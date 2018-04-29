/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.6.21-log : Database - kd_cs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kd_cs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kd_cs`;

/*Table structure for table `sys_column` */

DROP TABLE IF EXISTS `sys_column`;

CREATE TABLE `sys_column` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `field` varchar(50) NOT NULL COMMENT '字段名称',
  `width` int(10) NOT NULL DEFAULT '120' COMMENT '宽度',
  `sortable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可以排序',
  `title` varchar(50) NOT NULL COMMENT '标题名称',
  `grop_no` varchar(255) NOT NULL COMMENT '所属模块',
  `order_no` int(11) DEFAULT '1' COMMENT '排序号',
  `align` varchar(10) DEFAULT NULL COMMENT '对齐方式',
  ` indicate` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `sys_column` */

insert  into `sys_column`(`id`,`field`,`width`,`sortable`,`title`,`grop_no`,`order_no`,`align`,` indicate`) values ('1','caseCode',180,0,'案件序列号','case_info',1,'left','CIDC'),('19','caseCard',180,1,'卡号','case_info',19,'left','CIDC'),('18','caseNum',180,1,'证件号','case_info',18,'left','CIDC'),('22','caseAssignedName',95,0,'风控员','case_info',6,'left','CIDC'),('23','state',95,1,'案件状态','case_info',2,'left','CIDC'),('201','casemodel.caseName',95,0,'姓名','phone_record',0,'left',''),('302','name',95,0,'对象姓名','phone_record',0,'left',''),('7','caseName',95,0,'姓名','case_info',16,'left','CIDC'),('9','orgName',95,0,'风控方','case_info',5,'left','CIDC'),('10','collecStateId',95,1,'风控状态','case_info',23,'left','CIDC'),('11','batchCode',120,1,'批次号','case_info',3,'left','CIDC'),('12','caseMoney',95,1,'委案金额','case_info',9,'left','CIDC'),('20','mobile1',95,0,'手机号1','case_info',20,'left','CIDC'),('15','entrustName',120,0,'委托方','case_info',4,'left','CIDC'),('16','caseDate',95,1,'委案日期','case_info',7,'left','CIDC'),('21','mobile2',95,0,'手机号2','case_info',21,'left','CIDC'),('305','typeId',95,0,'风控结果','phone_record',0,'left',NULL),('334','brokerage',95,0,'业绩','case_info',15,'left','CIDC'),('125','surplusMoney',95,1,'剩余还款','case_info',10,'left','CIDC'),('126','ptpMoney',95,0,'PTP金额','case_info',11,'left','CIDC'),('127','cpMoney',95,1,'CP金额','case_info',12,'left','CIDC'),('128','paidNum',95,1,'已还款','case_info',13,'left','CIDC'),('304','contact',120,0,'电话/地址','phone_record',0,'left',NULL),('303','relation',95,0,'关系','phone_record',9,'left',NULL),('300','casemodel.caseCode',120,0,'案件序列号','phone_record',12,'left',NULL),('301','createTime',120,0,'风控时间','phone_record',5,'left',NULL),('306','content',120,0,'风控记录','phone_record',11,'left',NULL),('307','collecStateId',120,1,'风控状态','phone_record',1,'left',NULL),('308','ptpTime',120,0,'PTP日期','phone_record',13,'left',NULL),('309','employeeInfoModel.userName',120,0,'风控员','phone_record',17,'left',NULL),('310','ptpMoney',120,0,'PTP金额','phone_record',14,'left',NULL),('333','agentRate',60,0,'业绩率','case_info',14,'left','CIDC'),('400','collecStateId',120,0,'风控状态','visit_record',1,'left',NULL),('401','caseCode',120,0,'案件序列号','visit_record',1,'left',NULL),('402','name',120,0,'外访姓名','visit_record',1,'left',NULL),('403','relation',120,0,'关系','visit_record',1,'left',NULL),('404','caseMoney',120,0,'委案金额','visit_record',1,'left',NULL),('1013','cpTime',120,0,'CP日期','',100,NULL,'CIDC'),('406','visitAddress',120,0,'外访地址','visit_record',1,'left',NULL),('407','require',120,0,'外访要求','visit_record',1,'left',NULL),('408','userName',120,0,'风控员','visit_record',1,'left',NULL),('409','visitUser',120,0,'外访员','visit_record',1,'left',NULL),('410','isEffective',120,0,'地址是否有效','visit_record',1,'left',NULL),('411','actualVisitTime',120,0,'实际外访日期','visit_record',1,'left',NULL),('412','visitReport',120,0,'外访报告','visit_record',1,'left',NULL),('414','ename',120,0,'委托方','visit_record',1,'left',NULL),('415','visitState',120,0,'外访结果','visit_record',1,'left',NULL),('311','casemodel.caseCard',120,0,'卡号','phone_record',2,'left',NULL),('312','casemodel.accountNo',120,0,'账号','phone_record',3,'left',NULL),('313','casemodel.caseNum',120,1,'证件号','phone_record',4,'left',NULL),('314','prCat',120,0,'风控方式','phone_record',6,'left',NULL),('999','lastPhone',130,1,'最后通电时间','case_info1',22,'left',''),('1041','surplusPrincipal',120,0,'剩余本金','',128,NULL,'CIDC'),('335','caseBackdate',95,1,'退案日期','case_info',8,'left','CIDC'),('321','casemodel.remark1',120,0,'备注1','phone_record',18,'left',NULL),('322','casemodel.remark2',120,0,'备注2','phone_record',19,'left',NULL),('323','casemodel.state',120,0,'案件状态','phone_record',20,'left',NULL),('381','casemodel.surplusPrincipal',120,0,'剩余本金','phone_record',1,NULL,NULL),('1001','accountNo',130,0,'账号','',1,NULL,'CIDC'),('1002','caseFileNo',120,0,'档案号','',1,NULL,'CIDC'),('1003','currency',120,0,'币种','',1,NULL,'CIDC'),('1004','loanContract',120,0,'贷款合同号','',1,NULL,'CIDC'),('1005','overdueDays',120,0,'逾期天数','',1,NULL,'CIDC'),('1006','overdueAge',120,0,'账龄','',1,NULL,'CIDC'),('1007','endDate',120,0,'退案日期','',1,NULL,'CIDC'),('1008','entrustRate',120,0,'委托费率','',1,NULL,'CIDC'),('1010','loanStartdate',120,0,'贷款日期','',1,NULL,'CIDC'),('1011','remark1',120,0,'备注','',1,NULL,'CIDC'),('1012','salesDep',120,0,'营业部名称','',1,NULL,'CIDC'),('1042','domicile',120,0,'户籍地址','',129,NULL,'CIDC'),('361','casemodel.overdueMoney',120,0,'逾期金额','phone_record',1,NULL,NULL),('328','casemodel.loanContract',120,0,'贷款合同号','phone_record',21,NULL,NULL),('329','casemodel.caseFileNo',120,0,'档案号','phone_record',21,NULL,NULL),('330','casemodel.overdueDays',120,0,'逾期天数','phone_record',21,NULL,NULL),('331','casemodel.caseDate',120,0,'委案日期','phone_record',21,NULL,NULL),('332','casemodel.caseBackdate',120,0,'退案日期','phone_record',21,NULL,NULL),('364','casemodel.overdueAge',120,0,'账龄','phone_record',1,NULL,NULL),('365','casemodel.caseMoney',120,0,'委案金额','phone_record',1,NULL,NULL),('366','batchmodel.batchCode',120,0,'批次号','phone_record',1,NULL,NULL),('377','casemodel.caseAppNo',120,0,'申请单号','phone_record',1,NULL,NULL),('378','casemodel.commodity',120,0,'商品','phone_record',1,NULL,NULL),('380','contact',120,0,'电话\\地址类型','phone_record',1,NULL,NULL),('1014','lastPhone',120,0,'最后通电','',101,NULL,'CIDC'),('1015','caseNumId',120,0,'证件类型','',102,NULL,'CIDC'),('1016','bankAccount',120,0,'开户行','',103,NULL,'CIDC'),('1017','overdueMoney',120,0,'逾期金额','',104,NULL,'CIDC'),('1018','bill',120,0,'账单日','',105,NULL,'CIDC'),('1019','repaymentPeriods',120,0,'已还期数','',106,NULL,'CIDC'),('1020','overdueAge',120,0,'逾期账龄','',107,NULL,'CIDC'),('1021','overduePeriods',120,0,'逾期期数','',108,NULL,'CIDC'),('1022','overdueInterest',120,0,'逾期利息','',109,NULL,'CIDC'),('1023','lateFee',120,0,'滞纳金','',110,NULL,'CIDC'),('1024','creditId',120,0,'信贷分类','',111,NULL,'CIDC'),('1025','socialCardNo',120,0,'社保卡号','',112,NULL,'CIDC'),('1026','interestMoney',120,0,'欠息余额','',113,NULL,'CIDC'),('1027','monthRepayment',120,0,'每月还款金额','',114,NULL,'CIDC'),('1028','loanRate',120,0,'贷款利率','',115,NULL,'CIDC'),('1029','loanEnddate',120,0,'贷款截止日期','',116,NULL,'CIDC'),('1030','number',120,0,'型号','',117,NULL,'CIDC'),('1031','vinNo',120,0,'车架号','',118,NULL,'CIDC'),('1032','liceNo',120,0,'车牌号','',119,NULL,'CIDC'),('1033','brand',120,0,'品牌','',120,NULL,'CIDC'),('1034','principal',120,0,'本金','',121,NULL,'CIDC'),('1035','familyAddress',120,0,'本人家庭地址','',122,NULL,'CIDC'),('1036','familyPhone',120,0,'本人家庭电话','',123,NULL,'CIDC'),('1037','companyAddress',120,0,'本人单位地址','',124,NULL,'CIDC'),('1038','companyPhone',120,0,'本人单位电话','',125,NULL,'CIDC'),('1039','email',120,0,'邮箱','',126,NULL,'CIDC'),('1040','caseAppNo',120,0,'申请单号','',127,NULL,'CIDC'),('405','paidNum',120,0,'已还款','visit_record',1,'left',NULL),('1099','overdueAge',120,0,'逾期账龄','case_info',24,NULL,NULL),('316','casemodel.cusNo',120,0,'客户号','phone_record',15,'left',''),('360','casemodel.markId',120,0,'标ID','phone_record',21,'',''),('379','casePaidModel.sumMoney',120,0,'已还款','phone_record',1,'','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
