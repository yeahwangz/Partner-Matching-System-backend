-- 当向user表插入时，user_tag_forsearch也插入
DELIMITER $$

CREATE TRIGGER insert_table_user_tag_forsearch_by_user
    AFTER INSERT ON user
    FOR EACH ROW
BEGIN
    -- 向 user_tag_forsearch 表插入数据
    INSERT INTO user_tag_forsearch (userId, tags)
    VALUES (NEW.id, NEW.tags);
END$$

DELIMITER ;

-- 当user表更新时，user_tag_forsearch也更新
DELIMITER $$

CREATE TRIGGER update_table_user_tag_forsearch_by_user
    AFTER update ON user
    FOR EACH ROW
BEGIN
    -- user_tag_forsearch 表更新数据
    update user_tag_forsearch
        set tags = NEW.tags
    where userId = NEW.id;
END$$

DELIMITER ;

-- 当user表删除时，user_tag_forsearch也删除
DELIMITER $$

CREATE TRIGGER delete_table_user_tag_forsearch_by_user
    AFTER delete ON user
    FOR EACH ROW
BEGIN
    -- user_tag_forsearch 表更新数据
    delete from user_tag_forsearch
        where userId = OLD.id;
END$$

DELIMITER ;
