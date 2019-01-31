@REM
@set ModelName=tt
@set Path=E:\workspace\git\KGameServer\gameserver\src\main\java\com\game\model\
@set ModelPath=%Path%%ModelName%

echo %ModelPath%

if exist %ModelPath%
(
  echo "目录已存在"
)
else
(
    md %ModelPath%
)

pause;
