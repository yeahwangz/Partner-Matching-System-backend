SELECT JSON_VALID(currentMember) FROM team WHERE id = 13;

SELECT JSON_SEARCH (CAST('[100068, 4]' AS JSON), 'one', '100068'); -- 期望返回 "$[0]"

SELECT JSON_TYPE(JSON_EXTRACT('[100068, 4]', '$[0]')); -- 返回 "INTEGER"

SELECT JSON_SEARCH('[1, 2, 3]', 'one', 2); -- 预期返回 "$[1]"

SELECT JSON_SEARCH('[100068, 4,"a"]', 'one', 100068);

SELECT JSON_VALID('[100068, 4]');

SELECT @@sql_mode;

SELECT JSON_VALID('[100068, 4]');

SELECT VERSION();

SET @j = '["abc", [{"k": "10"}, "def"], {"x":"abc"}, {"y":"bcd"}]';
SELECT JSON_SEARCH(@j, 'one', 'abc');