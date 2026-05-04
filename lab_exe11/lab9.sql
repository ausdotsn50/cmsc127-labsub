-- department deletion safety
DELIMITER //

CREATE TRIGGER prevent_dep_del
BEFORE DELETE ON department
FOR EACH ROW
BEGIN

DECLARE dept_salary DECIMAL(8,2);

SELECT SUM(salary) INTO dept_salary
FROM instructor WHERE dept_name=OLD.dept_name;

IF dept_salary > 200000 THEN
	SIGNAL SQLSTATE '45000'
	SET MESSAGE_TEXT = "Error: you can't proceed in deleting this dept. Total salary of instructors > 200,000"
END IF;
END//
DELIMITER;

-- clasroom overbook prevention
DELIMITER //
CREATE TRIGGER clasroom_prevention
BEFORE INSERT ON section
FOR EACH ROW
BEGIN
	DECLARE room_matches INT;
	SELECT COUNT(*) INTO room_matches FROM section WHERE building=NEW.building AND
	room_number=NEW.room_number AND year=NEW.year AND semester=NEW.semester;

	IF room_matches > 0 THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Error: you can't have two sections assigned at one room at the same time";

	END IF;
END //
DELIMITER ;

// conflict
INSERT INTO section(course_id, sec_id, semester, year, building, room_number, time_slot_id) values('CS-101', 1,'Summer', 2017, 'Painter', 514, 'B'); 

// ok
INSERT INTO section(course_id, sec_id, semester, year, building, room_number, time_slot_id) values('CS-101', 1,'Summer', 2022, 'Painter', 514, 'B'); 


