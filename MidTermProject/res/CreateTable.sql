create database MidTermProject

use  MidTermProject

Create table ParkInfo(
parkId nvarchar(100) ,
areaId int,
areaName nvarchar(100),
parkName nvarchar(100),
totalSpace int,
surplusSpace int,
payGuide nvarchar(200),
introduction nvarchar(100),
address nvarchar(100),
wgsX decimal(7,4),
wgsY decimal(7,4),
)
drop table parkinfo
select * from Parkinfo
xp_readerrorlog 0, 1, N'Server is listening on' 
