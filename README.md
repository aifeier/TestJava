# TestJava
测试使用
##自定义实现日志类：LogUtils
<br>实现功能：
1. 消息日志必须包含时间戳和日志，时间戳的格式"年"-“月”-“日” “时”：“分”：“秒”."纳秒"，比如“2018-10-09 14:02:10.123456789”
2. 允许用户选择日志消息的输出模式：控制台（console）或者文件
3. 能对日志进行分级显示：INFO，DEBUG，WARNING，ERROR，FATAL
4. ERROR，FATAL等级的日志信息需要分别保存在err.log和fatal.log文件
