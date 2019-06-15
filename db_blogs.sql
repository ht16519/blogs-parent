/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : db_blogs

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-06-15 11:26:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mto_attachs
-- ----------------------------
DROP TABLE IF EXISTS `mto_attachs`;
CREATE TABLE `mto_attachs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `height` int(11) NOT NULL,
  `original` varchar(255) DEFAULT NULL,
  `preview` varchar(255) DEFAULT NULL,
  `screenshot` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `store` int(11) NOT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  `width` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_attachs
-- ----------------------------

-- ----------------------------
-- Table structure for mto_feeds
-- ----------------------------
DROP TABLE IF EXISTS `mto_feeds`;
CREATE TABLE `mto_feeds` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态id',
  `author_id` int(11) DEFAULT NULL COMMENT '文章作者id',
  `create_time` datetime NOT NULL,
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `type` int(2) NOT NULL COMMENT '所属类型id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_feeds
-- ----------------------------

-- ----------------------------
-- Table structure for mto_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `mto_friend_link`;
CREATE TABLE `mto_friend_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `logo` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `site_name` varchar(255) NOT NULL,
  `sort` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_friend_link
-- ----------------------------

-- ----------------------------
-- Table structure for mto_users_open_oauth
-- ----------------------------
DROP TABLE IF EXISTS `mto_users_open_oauth`;
CREATE TABLE `mto_users_open_oauth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `expire_in` varchar(255) DEFAULT NULL,
  `oauth_code` varchar(255) DEFAULT NULL,
  `oauth_type` int(11) DEFAULT NULL,
  `oauth_user_id` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_users_open_oauth
-- ----------------------------

-- ----------------------------
-- Table structure for mto_verify
-- ----------------------------
DROP TABLE IF EXISTS `mto_verify`;
CREATE TABLE `mto_verify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(60) NOT NULL,
  `created` datetime NOT NULL,
  `expired` datetime NOT NULL,
  `status` int(11) DEFAULT NULL,
  `target` varchar(96) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m7p0b526c4xlgjn787t22om2g` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_verify
-- ----------------------------

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `author_id` int(11) NOT NULL COMMENT '作者id',
  `comments` int(8) NOT NULL DEFAULT '0' COMMENT '评论数量',
  `tag_ids` varchar(255) DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL COMMENT '内容表达式',
  `favors` int(8) NOT NULL DEFAULT '0' COMMENT '收藏量',
  `featured` int(8) NOT NULL DEFAULT '-10' COMMENT '是否推荐（10，-10：是，否）',
  `belong_group` int(8) NOT NULL COMMENT '所属组',
  `images` int(2) NOT NULL DEFAULT '0' COMMENT '图片数量',
  `last_image_id` int(11) NOT NULL DEFAULT '0' COMMENT '最后一张图片的id',
  `status` int(2) NOT NULL DEFAULT '10' COMMENT '文章状态（-1：已删除，1：审核中，10：正常）',
  `summary` varchar(255) NOT NULL COMMENT '摘要信息',
  `tags` varchar(255) DEFAULT NULL COMMENT '所属标签',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '文章类型是否原创（1，0：是，否）',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `views` int(8) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `no_tags` (`tags`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', '1', '3', '1,2,3', null, '22', '10', '1', '1', '1', '10', 'CSDN还提供以下服务：  1、每周直播+定期知识点大串讲，老师帮你捋脉络；  2、社群助教+导师+班主任全天候答疑服务，你的问题永远不过夜；  3、科学且系统化的学习路线，闯关式学习，保证学习效率。  自学没有人管束，很难学成。即使有很强的自制...', 'java', '1', '以低价', '23', '2019-04-23 16:22:10', null);
INSERT INTO `t_article` VALUES ('2', '1', '0', '11,2,3', null, '22', '10', '1', '3', '4', '10', '大家熟悉使用IDEA的都知道Live Templates的自定义功能，可谓是大大增加了我们的开发速度 但是postfix以前一直是使用固定的配置，没有办法自定义 &nbsp; 偶然的机会打开postfix看到一个  此处不知何时添加了一个post...', 'java', '1', '撒旦发送', '13', '2019-04-23 16:23:51', null);
INSERT INTO `t_article` VALUES ('3', '1', '4', '22,3', '$!{site_editor}', '19', '10', '1', '0', '0', '10', '这几天在工作中新使用springboot 然后遇到了乱码问题 springboot 的配置文件方式和之前的srping mvc 有很大不同就让我很是困惑，先讲解我们开始使用的解决方案：在application.properties 中增加spri...', 'java', '1', '华宇五环大厦', '11', '2019-04-24 19:31:49', null);
INSERT INTO `t_article` VALUES ('5', '1', '16', '2,3', '$!{site_editor}', '8', '10', '1', '0', '0', '10', '开宗明义--解决办法：1、先排查字段拼写错误等等问题2、补充一个比较坑的问题，查看如下两个路径的文件看看编译文件有没有更新。查看路径如果并未更新，清掉编译文件：清除路径3、查看是否导入下列几个包：jackson-core-2.8.4.jarjac...', 'java,html', '1', '第1集', '11', '2019-04-24 20:16:17', null);
INSERT INTO `t_article` VALUES ('6', '1', '0', '4,5', '$!{site_editor}', '7', '10', '1', '0', '0', '10', '开宗明义--解决办法：1、先排查字段拼写错误等等问题2、补充一个比较坑的问题，查看如下两个路径的文件看看编译文件有没有更新。查看路径如果并未更新，清掉编译文件：清除路径3、查看是否导入下列几个包：jackson-core-2.8.4.jarjac...', 'java', '1', '1221', '1', '2019-04-24 20:19:14', null);
INSERT INTO `t_article` VALUES ('7', '1', '0', null, '$!{site_editor}', '0', '10', '1', '0', '0', '10', '最后我们可以查看浏览器 Cookie，发现里面有我们创建的 Cookie&nbsp;&nbsp;本文链接：https://liuyanzhao.com/6175.html', 'java', '1', '我的世界', '0', '2019-05-03 08:38:38', null);
INSERT INTO `t_article` VALUES ('8', '1', '0', null, '$!{site_editor}', '0', '10', '1', '0', '0', '10', '2.然后在命令行执行&nbsp;composer update&nbsp;，我使用的是&nbsp;PhpStorm&nbsp;,所以可以用&nbsp;alt+F12&nbsp;调出命令行，输入即可。&nbsp;', 'java', '1', '博客统计', '0', '2019-05-03 08:42:10', null);
INSERT INTO `t_article` VALUES ('9', '1', '3', null, '$!{site_editor}', '1', '10', '1', '0', '0', '10', '修改已经运行半年的项目，实时从第三方获取数据，最近出现一个问题，排查了好久，才发现是group_concat长度限制的问题，默认是1024&nbsp;(select GROUP_CONCAT(nd.record_date) from jk_dat...', 'mysql', '1', 'mysql 中group_concat长度设置', '0', '2019-05-03 08:57:18', null);
INSERT INTO `t_article` VALUES ('10', '1', '0', null, '$!{site_editor}', '0', '10', '1', '0', '0', '10', '&nbsp;在使用bootstrap的插件的时候，需要导入一些css、js。1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;link&nbsp;href=\"/static/css/bootstrap.min.css\"&nbs...', 'html,css,js', '1', '基于bootstarp的分页插件使用', '0', '2019-05-03 09:08:15', null);
INSERT INTO `t_article` VALUES ('11', '1', '0', null, '$!{site_editor}', '0', '10', '1', '0', '0', '10', '1. freemarker获取list的size ：&nbsp;JavaArrayList&lt;String&gt;&nbsp;list&nbsp;=&nbsp;new&nbsp;ArrayList&lt;String&gt;();Freema...', 'freemarker', '1', ' freemarker', '0', '2019-05-03 11:03:29', null);
INSERT INTO `t_article` VALUES ('12', '1', '0', null, '$!{site_editor}', '0', '10', '1', '0', '0', '10', '关于在freemarker模板中遍历数据模型List&lt;JavaBean&gt;的经验&nbsp; &nbsp; &nbsp; &nbsp;本文采用简单的servlet作为后台处理数据的工具，前台使用freemarker的ftl模板作为输出工...', 'free', '1', '关于在freemarker模板中遍历数据模型List<JavaB', '0', '2019-05-03 11:04:07', null);
INSERT INTO `t_article` VALUES ('13', '1', '0', null, '$!{site_editor}', '0', '10', '1', '0', '0', '10', '接着看我写的newsSql.xml文件中查询所有News对象的并返回List&lt;News&gt;或者Map&lt;String,News&gt;型数据的配置信息：&lt;typeAlias alias=\"news\" type=\"cn.doma...', 'java', '1', 'code', '0', '2019-05-03 11:04:25', null);
INSERT INTO `t_article` VALUES ('14', '1', '0', null, '$!{site_editor}', '1', '10', '1', '0', '0', '10', '来吗？？？5555555555', '游戏', '1', '123', '1', '2019-05-07 20:55:49', '2019-05-08 21:37:29');
INSERT INTO `t_article` VALUES ('15', '1', '0', null, '$!{site_editor}', '0', '10', '1', '0', '0', '-10', '123123', '123', '1', '123', '0', '2019-05-07 21:06:30', null);
INSERT INTO `t_article` VALUES ('16', '1', '0', null, '$!{site_editor}', '1', '10', '1', '0', '0', '10', 'centos7 安装MySQL7 并更改初始化密码1、官方安装文档http://dev.mysql.com/doc/mysql-yum-repo-quick-guide/en/2、下载 Mysql yum包http://dev.mysql.com...', 'linux', '1', 'centos安装mysql', '1', '2019-05-09 14:10:49', null);
INSERT INTO `t_article` VALUES ('17', '1', '1', null, '$!{site_editor}', '0', '10', '1', '0', '0', '10', '123123', '111', '1', '1221', '0', '2019-05-12 08:33:18', null);
INSERT INTO `t_article` VALUES ('18', '1', '0', null, '$!{site_editor}', '0', '-10', '1', '0', '0', '10', 'dasdas', 'wwww', '1', '第1集', '1', '2019-05-12 09:35:37', null);
INSERT INTO `t_article` VALUES ('19', '1', '0', null, '$!{site_editor}', '0', '-10', '1', '0', '0', '10', 'dsadsa', '222', '0', 'ewqeqwewq', '0', '2019-05-12 09:36:12', null);
INSERT INTO `t_article` VALUES ('20', '1', '0', null, '$!{site_editor}', '0', '-10', '1', '0', '0', '10', 'dsadasdsadsadasdasdas', 'aaa', '0', '第1集', '0', '2019-05-12 12:12:01', null);
INSERT INTO `t_article` VALUES ('21', '1', '0', null, '$!{site_editor}', '2', '-10', '1', '0', '0', '10', 'Maven 构建项目1、访问 http://start.spring.io/2、选择构建工具 Maven Project、Java、Spring Boot 版本 2.1.3 以及一些工程基本信息，可参考下图所示：3、点击 Generate Pro...', 'spring', '1', '广告23333', '1', '2019-05-21 16:29:15', null);
INSERT INTO `t_article` VALUES ('22', '1', '0', null, '$!{site_editor}', '1', '-10', '1', '0', '0', '10', '当使用Springboot自带上传时，超出最大上传限制后会返回500.第一种处理方式，（没有成功，但能捕获到异常，无法正常显示输出信息）：&nbsp;通过异常捕获，问题：没有正常返回指定信息。&nbsp;@RestControllerAdvice...', '难受', '1', '这是难受', '1', '2019-05-30 20:52:17', '2019-05-30 21:31:48');
INSERT INTO `t_article` VALUES ('23', '1', '0', null, '$!{site_editor}', '0', '-10', '1', '0', '0', '10', '', '111', '1', '222222', '1', '2019-06-06 11:23:24', null);
INSERT INTO `t_article` VALUES ('24', '11', '0', null, '$!{site_editor}', '0', '-10', '1', '0', '0', '10', '前言&nbsp;&nbsp;&nbsp;&nbsp;Java码农不识Apache，敲尽一生也枉然。旗下的开源项目众多，各个都是吊炸天。今日且说Commons,轻轻点击此链接进入Apache&nbsp;Commons主页，Logging、Pool、...', 'java', '0', 'commons.lang中常用的工具', '1', '2019-06-06 11:27:49', '2019-06-06 11:34:46');

-- ----------------------------
-- Table structure for t_article_accessory
-- ----------------------------
DROP TABLE IF EXISTS `t_article_accessory`;
CREATE TABLE `t_article_accessory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `width` int(8) DEFAULT NULL COMMENT '宽',
  `height` int(8) DEFAULT NULL COMMENT '高',
  `original` varchar(255) NOT NULL COMMENT '源路径',
  `preview` varchar(255) NOT NULL COMMENT '预览路径',
  `screenshot` varchar(255) DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '10' COMMENT '图片状态（1-，10：失效，正常）',
  `store` int(2) DEFAULT '2' COMMENT '来源（1，2：网络，本地）',
  `to_id` int(20) NOT NULL COMMENT '所属文章id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article_accessory
-- ----------------------------
INSERT INTO `t_article_accessory` VALUES ('5', null, null, '//upload-images.jianshu.io/upload_images/2802381-cc07ccad833e1043.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/381/format/webp', '//upload-images.jianshu.io/upload_images/2802381-cc07ccad833e1043.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/381/format/webp', null, '10', '2', '5');
INSERT INTO `t_article_accessory` VALUES ('6', null, null, '//upload-images.jianshu.io/upload_images/2802381-b3196d0dba87c926.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/384/format/webp', '//upload-images.jianshu.io/upload_images/2802381-b3196d0dba87c926.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/384/format/webp', null, '10', '2', '5');
INSERT INTO `t_article_accessory` VALUES ('7', null, null, '//upload-images.jianshu.io/upload_images/2802381-74c0e75da2cadeac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/611/format/webp', '//upload-images.jianshu.io/upload_images/2802381-74c0e75da2cadeac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/611/format/webp', null, '10', '2', '5');
INSERT INTO `t_article_accessory` VALUES ('8', null, null, '//upload-images.jianshu.io/upload_images/2802381-39992ccbb433560e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/654/format/webp', '//upload-images.jianshu.io/upload_images/2802381-39992ccbb433560e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/654/format/webp', null, '10', '2', '5');
INSERT INTO `t_article_accessory` VALUES ('9', null, null, 'http://images2015.cnblogs.com/blog/816787/201702/816787-20170215103829269-1581468016.png', 'http://images2015.cnblogs.com/blog/816787/201702/816787-20170215103829269-1581468016.png', null, '10', '1', '6');
INSERT INTO `t_article_accessory` VALUES ('10', null, null, 'https://img-blog.csdn.net/20171227133558110', 'https://img-blog.csdn.net/20171227133558110', null, '10', '1', '7');
INSERT INTO `t_article_accessory` VALUES ('11', null, null, 'https://img-blog.csdn.net/20170317184538064?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdmlzaW9udGltZQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast', 'https://img-blog.csdn.net/20170317184538064?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdmlzaW9udGltZQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast', null, '10', '1', '8');
INSERT INTO `t_article_accessory` VALUES ('12', null, null, 'https://images2015.cnblogs.com/blog/959846/201702/959846-20170213132313332-23161744.png', 'https://images2015.cnblogs.com/blog/959846/201702/959846-20170213132313332-23161744.png', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('13', null, null, 'https://images2015.cnblogs.com/blog/959846/201702/959846-20170213132649410-1258012029.png', 'https://images2015.cnblogs.com/blog/959846/201702/959846-20170213132649410-1258012029.png', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('14', null, null, 'https://images2015.cnblogs.com/blog/959846/201702/959846-20170213132732050-1053172761.png', 'https://images2015.cnblogs.com/blog/959846/201702/959846-20170213132732050-1053172761.png', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('15', null, null, 'https://common.cnblogs.com/images/copycode.gif', 'https://common.cnblogs.com/images/copycode.gif', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('16', null, null, 'https://common.cnblogs.com/images/copycode.gif', 'https://common.cnblogs.com/images/copycode.gif', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('17', null, null, 'https://common.cnblogs.com/images/copycode.gif', 'https://common.cnblogs.com/images/copycode.gif', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('18', null, null, 'https://common.cnblogs.com/images/copycode.gif', 'https://common.cnblogs.com/images/copycode.gif', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('19', null, null, 'https://common.cnblogs.com/images/copycode.gif', 'https://common.cnblogs.com/images/copycode.gif', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('20', null, null, 'https://common.cnblogs.com/images/copycode.gif', 'https://common.cnblogs.com/images/copycode.gif', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('21', null, null, 'https://common.cnblogs.com/images/copycode.gif', 'https://common.cnblogs.com/images/copycode.gif', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('22', null, null, 'https://common.cnblogs.com/images/copycode.gif', 'https://common.cnblogs.com/images/copycode.gif', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('23', null, null, 'https://images2015.cnblogs.com/blog/959846/201702/959846-20170213134322457-1467654290.png', 'https://images2015.cnblogs.com/blog/959846/201702/959846-20170213134322457-1467654290.png', null, '-10', '1', '10');
INSERT INTO `t_article_accessory` VALUES ('26', null, null, '/accessory/admin/article_20190507210603753.gif', '/accessory/admin/article_20190507210603753.gif', null, '-10', '2', '15');
INSERT INTO `t_article_accessory` VALUES ('32', null, null, '/accessory/admin/article_20190508182207661.gif', '/accessory/admin/article_20190508182207661.gif', null, '10', '1', '14');
INSERT INTO `t_article_accessory` VALUES ('33', null, null, '/accessory/admin/article_20190508182218978.jpg', '/accessory/admin/article_20190508182218978.jpg', null, '10', '1', '14');
INSERT INTO `t_article_accessory` VALUES ('34', null, null, '/accessory/admin/article_20190508213711987.jpg', '/accessory/admin/article_20190508213711987.jpg', null, '10', '1', '14');
INSERT INTO `t_article_accessory` VALUES ('35', null, null, 'https://upload-images.jianshu.io/upload_images/1342351-dbe0a0ebb51aa98c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'https://upload-images.jianshu.io/upload_images/1342351-dbe0a0ebb51aa98c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', null, '10', '1', '16');
INSERT INTO `t_article_accessory` VALUES ('36', null, null, 'https://upload-images.jianshu.io/upload_images/1342351-c62f0707f1100946.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'https://upload-images.jianshu.io/upload_images/1342351-c62f0707f1100946.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', null, '10', '1', '16');
INSERT INTO `t_article_accessory` VALUES ('37', null, null, 'https://upload-images.jianshu.io/upload_images/1342351-6670404dea32c709.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'https://upload-images.jianshu.io/upload_images/1342351-6670404dea32c709.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', null, '10', '1', '16');
INSERT INTO `t_article_accessory` VALUES ('38', null, null, 'https://upload-images.jianshu.io/upload_images/1342351-2dff68de78dca1aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'https://upload-images.jianshu.io/upload_images/1342351-2dff68de78dca1aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', null, '10', '1', '16');
INSERT INTO `t_article_accessory` VALUES ('39', null, null, 'http://www.itmind.net/assets/images/2019/springboot/spring-boot-start.png', 'http://www.itmind.net/assets/images/2019/springboot/spring-boot-start.png', null, '10', '1', '21');
INSERT INTO `t_article_accessory` VALUES ('40', null, null, 'https://images2015.cnblogs.com/blog/331425/201607/331425-20160712105138029-1564953731.png', 'https://images2015.cnblogs.com/blog/331425/201607/331425-20160712105138029-1564953731.png', null, '10', '1', '21');
INSERT INTO `t_article_accessory` VALUES ('41', null, null, '/accessory/admin/article_20190606112007688.png', '/accessory/admin/article_20190606112007688.png', null, '10', '1', '23');
INSERT INTO `t_article_accessory` VALUES ('42', null, null, '/accessory/admin/article_20190606112007688.png', '/accessory/admin/article_20190606112007688.png', null, '10', '1', '23');

-- ----------------------------
-- Table structure for t_article_content
-- ----------------------------
DROP TABLE IF EXISTS `t_article_content`;
CREATE TABLE `t_article_content` (
  `id` int(11) NOT NULL COMMENT '文章id',
  `content` longtext NOT NULL COMMENT '文章内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article_content
-- ----------------------------
INSERT INTO `t_article_content` VALUES ('3', '<html>\n <head></head>\n <body>\n  <p><span>这几天在工作中新使用springboot 然后遇到了乱码问题 springboot 的配置文件方式和之前的srping mvc 有很大不同就让我很是困惑，先讲解我们开始使用的解决方案：</span></p>\n  <p><span>在application.properties 中增加</span></p>\n  <p><span>spring.http.encoding.force=true</span></p>\n  <p><span>spring.http.encoding.charset=UTF-8</span></p>\n  <p><span>spring.http.encoding.enabled=true</span></p>\n  <p><span>server.tomcat.uri-encoding=UTF-8</span></p>\n  <p><span><br></span></p>\n  <p><span>用来解决乱码问题</span></p>\n  <p><span>然后发现在拦截器中返回的中文已经不乱码了。</span></p>\n  <p><span>在后续测试中发现controller中返回的数据依旧乱码，于是在</span></p>\n  <p><span>@RequestMapping中增加produces=\"text/plain;charset=UTF-8\"</span></p>\n  <p><span><br></span></p>\n  <p><span><br></span></p>\n  <p><span><br></span></p>\n  <p><span>但是总觉得要限定了请求的数据类型，所以继续研究，然后在查找的时候发现了HttpMessageConverter类 ，在其中的方法</span></p>\n  <p><span><br></span></p>\n  <p><span>protected Long getContentLength(String str, MediaType contentType) {</span></p>\n  <p><span>&nbsp; &nbsp; Charset charset = this.getContentTypeCharset(contentType);</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp; &nbsp; try {</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp; &nbsp; return Long.valueOf((long)str.getBytes(charset.name()).length);</span></p>\n  <p><span>&nbsp; &nbsp; } catch (UnsupportedEncodingException var5) {</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp; &nbsp; throw new IllegalStateException(var5);</span></p>\n  <p><span>&nbsp; &nbsp; }</span></p>\n  <p><span>}</span></p>\n  <p><span>和</span></p>\n  <p><span>private Charset getContentTypeCharset(MediaType contentType) {</span></p>\n  <p><span>&nbsp; &nbsp; return contentType != null &amp;&amp; contentType.getCharset() != null?contentType.getCharset():this.getDefaultCharset();</span></p>\n  <p><span>}</span></p>\n  <p><span>中发现 getContentTypeCharset的MediaType是入参的数据 里面的utf-8然后在getContentLength的MediaType 的编码是ISO-8859-1 看了下这个类中</span></p>\n  <p><span>ublic static final Charset DEFAULT_CHARSET = Charset.forName(\"ISO-8859-1\");</span></p>\n  <p><span>---------------------&nbsp;</span></p>\n  <p><span>作者：wujianmin577&nbsp;</span></p>\n  <p><span>来源：CSDN&nbsp;</span></p>\n  <p><span>原文：https://blog.csdn.net/wujianmin577/article/details/61197485&nbsp;</span></p>\n  <p><span>版权声明：本文为博主原创文章，转载请附上博文链接！</span></p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('5', '<html>\n <head></head>\n <body>\n  <h5>开宗明义--解决办法：</h5>\n  <p>1、先排查字段拼写错误等等问题<br>2、补充一个比较坑的问题，查看如下两个路径的文件看看编译文件有没有更新。</p>\n  <p><img data-original-src=\"//upload-images.jianshu.io/upload_images/2802381-cc07ccad833e1043.png\" data-original-width=\"381\" data-original-height=\"289\" data-original-format=\"image/png\" data-original-filesize=\"14879\" style=\"cursor: zoom-in;\" class=\"\" src=\"//upload-images.jianshu.io/upload_images/2802381-cc07ccad833e1043.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/381/format/webp\"></p>\n  <p>查看路径</p>\n  <p>如果并未更新，清掉编译文件：</p>\n  <p><img data-original-src=\"//upload-images.jianshu.io/upload_images/2802381-b3196d0dba87c926.png\" data-original-width=\"384\" data-original-height=\"299\" data-original-format=\"image/png\" data-original-filesize=\"18551\" style=\"cursor: zoom-in;\" class=\"\" src=\"//upload-images.jianshu.io/upload_images/2802381-b3196d0dba87c926.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/384/format/webp\"></p>\n  <p>清除路径</p>\n  <p>3、查看是否导入下列几个包：</p>\n  <pre class=\"hljs css\">jackson-core-2.8.4.jarjackson-annotations-2.8.4.jarjackson-databind-2.8.4.jar</pre>\n  <p>maven导入</p>\n  <pre class=\"hljs xml\">&nbsp;&nbsp;&nbsp;&nbsp;&lt;dependency&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;artifactId&gt;jackson-core&lt;/artifactId&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;version&gt;2.8.4&lt;/version&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;/dependency&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;dependency&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;artifactId&gt;jackson-annotations&lt;/artifactId&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;version&gt;2.8.4&lt;/version&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;/dependency&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;dependency&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;artifactId&gt;jackson-databind&lt;/artifactId&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;version&gt;2.8.4&lt;/version&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;/dependency&gt;</pre>\n  <p>4、再补充一个：必须保证请求头的Content-Type为application/json<br>即使用ajax的话，必须加上<code>contentType : \'application/json\'</code><br>如果使用Postman测试的话如下操作：<br>①、在Headers添加：</p>\n  <p><img data-original-src=\"//upload-images.jianshu.io/upload_images/2802381-74c0e75da2cadeac.png\" data-original-width=\"611\" data-original-height=\"109\" data-original-format=\"image/png\" data-original-filesize=\"7668\" style=\"cursor: zoom-in;\" class=\"\" src=\"//upload-images.jianshu.io/upload_images/2802381-74c0e75da2cadeac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/611/format/webp\"></p>\n  <p>请求头</p>\n  <p>②、在body中使用json传值</p>\n  <p><img data-original-src=\"//upload-images.jianshu.io/upload_images/2802381-39992ccbb433560e.png\" data-original-width=\"654\" data-original-height=\"128\" data-original-format=\"image/png\" data-original-filesize=\"12382\" style=\"cursor: zoom-in;\" class=\"\" src=\"//upload-images.jianshu.io/upload_images/2802381-39992ccbb433560e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/654/format/webp\"></p>\n  <p>body</p>\n  <p><br><br>作者：steamed_bun<br>链接：https://www.jianshu.com/p/a5ca1fc63f81<br>来源：简书<br>简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。</p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('6', '<html>\n <head></head>\n <body>\n  <p style=\"box-sizing: border-box; outline: 0px; margin-top: 0px; margin-bottom: 16px; padding: 0px; font-family: verdana, Arial, Helvetica, sans-serif; font-size: 14px; color: rgb(51, 51, 51); line-height: 26px; overflow-x: auto; overflow-wrap: break-word; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"http://images2015.cnblogs.com/blog/816787/201702/816787-20170215103829269-1581468016.png\" alt=\"\" style=\"box-sizing: border-box; outline: 0px; margin: 0px; padding: 0px; max-width: 100%; overflow-wrap: break-word; cursor: zoom-in; border: 0px;\"></p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('7', '<html>\n <head></head>\n <body>\n  <p style=\"box-sizing: border-box; outline: 0px; margin-top: 0px; margin-bottom: 16px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; color: rgb(79, 79, 79); line-height: 26px; overflow-x: auto; overflow-wrap: break-word; white-space: normal; background-color: rgb(255, 255, 255);\">最后我们可以查看浏览器 Cookie，发现里面有我们创建的 Cookie</p>\n  <p style=\"box-sizing: border-box; outline: 0px; margin-top: 0px; margin-bottom: 16px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; color: rgb(79, 79, 79); line-height: 26px; overflow-x: auto; overflow-wrap: break-word; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"https://img-blog.csdn.net/20171227133558110\" alt=\"\" style=\"box-sizing: border-box; outline: 0px; margin: 0px; padding: 0px; max-width: 100%; overflow-wrap: break-word; cursor: zoom-in;\"><br style=\"box-sizing: border-box; outline: 0px; margin: 0px; padding: 0px; overflow-wrap: break-word;\"></p>\n  <p style=\"box-sizing: border-box; outline: 0px; margin-top: 0px; margin-bottom: 16px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; color: rgb(79, 79, 79); line-height: 26px; overflow-x: auto; overflow-wrap: break-word; white-space: normal; background-color: rgb(255, 255, 255);\">&nbsp;</p>\n  <p style=\"box-sizing: border-box; outline: 0px; margin-top: 0px; margin-bottom: 16px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; color: rgb(79, 79, 79); line-height: 26px; overflow-x: auto; overflow-wrap: break-word; white-space: normal; background-color: rgb(255, 255, 255);\">&nbsp;</p>\n  <p style=\"box-sizing: border-box; outline: 0px; margin-top: 0px; margin-bottom: 16px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; color: rgb(79, 79, 79); line-height: 26px; overflow-x: auto; overflow-wrap: break-word; white-space: normal; background-color: rgb(255, 255, 255);\">本文链接：<a href=\"https://liuyanzhao.com/6175.html\" rel=\"nofollow\" target=\"_blank\" style=\"box-sizing: border-box; outline: 0px; margin: 0px; padding: 0px; color: rgb(103, 149, 181); text-decoration-line: none; cursor: pointer; overflow-wrap: break-word;\">https://liuyanzhao.com/6175.html</a></p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('8', '<html>\n <head></head>\n <body>\n  <p><span style=\"color: rgb(79, 79, 79); font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; font-variant-ligatures: common-ligatures; background-color: rgb(255, 255, 255);\">2.然后在命令行执行&nbsp;</span><code style=\"box-sizing: border-box; outline: 0px; margin: 0px; padding: 2px 4px; font-family: &quot;Source Code Pro&quot;, &quot;DejaVu Sans Mono&quot;, &quot;Ubuntu Mono&quot;, &quot;Anonymous Pro&quot;, &quot;Droid Sans Mono&quot;, Menlo, Monaco, Consolas, Inconsolata, Courier, monospace, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, sans-serif; font-size: 14px; line-height: 22px; color: rgb(199, 37, 78); background-color: rgb(249, 242, 244); border-radius: 2px; overflow-wrap: break-word; font-variant-ligatures: common-ligatures; white-space: normal;\">composer update</code><span style=\"color: rgb(79, 79, 79); font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; font-variant-ligatures: common-ligatures; background-color: rgb(255, 255, 255);\">&nbsp;，我使用的是&nbsp;</span><code style=\"box-sizing: border-box; outline: 0px; margin: 0px; padding: 2px 4px; font-family: &quot;Source Code Pro&quot;, &quot;DejaVu Sans Mono&quot;, &quot;Ubuntu Mono&quot;, &quot;Anonymous Pro&quot;, &quot;Droid Sans Mono&quot;, Menlo, Monaco, Consolas, Inconsolata, Courier, monospace, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, sans-serif; font-size: 14px; line-height: 22px; color: rgb(199, 37, 78); background-color: rgb(249, 242, 244); border-radius: 2px; overflow-wrap: break-word; font-variant-ligatures: common-ligatures; white-space: normal;\">PhpStorm</code><span style=\"color: rgb(79, 79, 79); font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; font-variant-ligatures: common-ligatures; background-color: rgb(255, 255, 255);\">&nbsp;,所以可以用&nbsp;</span><code style=\"box-sizing: border-box; outline: 0px; margin: 0px; padding: 2px 4px; font-family: &quot;Source Code Pro&quot;, &quot;DejaVu Sans Mono&quot;, &quot;Ubuntu Mono&quot;, &quot;Anonymous Pro&quot;, &quot;Droid Sans Mono&quot;, Menlo, Monaco, Consolas, Inconsolata, Courier, monospace, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, sans-serif; font-size: 14px; line-height: 22px; color: rgb(199, 37, 78); background-color: rgb(249, 242, 244); border-radius: 2px; overflow-wrap: break-word; font-variant-ligatures: common-ligatures; white-space: normal;\">alt+F12</code><span style=\"color: rgb(79, 79, 79); font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; font-variant-ligatures: common-ligatures; background-color: rgb(255, 255, 255);\">&nbsp;调出命令行，输入即可。&nbsp;</span><br style=\"box-sizing: border-box; outline: 0px; margin: 0px; padding: 0px; overflow-wrap: break-word; color: rgb(79, 79, 79); font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; font-variant-ligatures: common-ligatures; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"https://img-blog.csdn.net/20170317184538064?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdmlzaW9udGltZQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"composer update\" title=\"\" style=\"box-sizing: border-box; outline: 0px; margin: 24px 0px; padding: 0px; border-style: none; max-width: 100%; overflow-wrap: break-word; cursor: zoom-in; color: rgb(79, 79, 79); font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; font-variant-ligatures: common-ligatures; white-space: normal; background-color: rgb(255, 255, 255);\"></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('9', '<html>\n <head></head>\n <body>\n  <p><span>修改已经运行半年的项目，实时从第三方获取数据，最近出现一个问题，排查了好久，才发现是group_concat长度限制的问题，默认是1024</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span><br></span></p>\n  <p><span>(select GROUP_CONCAT(nd.record_date) from jk_data_day nd where nd.itemid = 10 and nd.devid = 1433628673 AND</span></p>\n  <p><span>&nbsp; &nbsp;nd.record_date BETWEEN date_add(\'2017-12-15 15:13\', interval -89 DAY) and date_add(\'2017-12-15 15:13\', interval 59 SECOND)) as recordDates</span></p>\n  <p><span>在网上搜下解决的方法，主要是有两个方法：</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp; 1.彻底修改，找到MYSQL的配置文件my.ini或者my.cnf,在里面添加以下信息</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp; group_concat_max_len = -1&nbsp; # -1为最大值或填入你要的最大长度</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp; 或者自己设置group_concat_max_len = 1024000。</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp; &nbsp; &nbsp; &nbsp; 注意：需要重启MySQL才能生效。</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp;2.在客户端执行语句解决:</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp; &nbsp; SET GLOBAL group_concat_max_len=1024000;</span></p>\n  <p><span>&nbsp; &nbsp; SET SESSION group_concat_max_len=1024000;</span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span><br></span></p>\n  <p><span>查询是否配置成功的命令：</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp; &nbsp; &nbsp; &nbsp;show variables like \"group_concat_max_len\";</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span><br></span></p>\n  <p><span>-------------------------------------------------如何重启MySQL服务----------------------------------------------------</span></p>\n  <p><span><br></span></p>\n  <p><span>RedHat Linux (Fedora Core/Cent OS)</span></p>\n  <p><span>1.启动：/etc/init.d/mysqld start</span></p>\n  <p><span>2.停止：/etc/init.d/mysqld stop</span></p>\n  <p><span>3.重启：/etc/init.d/mysqld restart</span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span>Debian / Ubuntu Linux</span></p>\n  <p><span>1.启动：/etc/init.d/mysql start</span></p>\n  <p><span>2.停止：/etc/init.d/mysql stop</span></p>\n  <p><span>3.重启：/etc/init.d/mysql restart</span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span>Windows</span></p>\n  <p><span>1.点击“开始”-&gt;“运行”（快捷键Win+R）</span></p>\n  <p><span>2.启动：输入 net stop mysql</span></p>\n  <p><span>3.停止：输入 net start mysql</span></p>\n  <p><span><br></span></p>\n  <p><span><br></span></p>\n  <p><span>提示</span></p>\n  <p><span>Redhat Linux / Ubuntu也支持service command，</span></p>\n  <p><span>启动：# service mysqld start</span></p>\n  <p><span>停止：# service mysqld stop</span></p>\n  <p><span>重启：# service mysqld restart</span></p>\n  <p><span>Windows下不能直接重启(restart)，只能先停止，再启动。</span></p>\n  <p><span>---------------------&nbsp;</span></p>\n  <p><span>作者：xuforeverlove&nbsp;</span></p>\n  <p><span>来源：CSDN&nbsp;</span></p>\n  <p><span>原文：https://blog.csdn.net/xuforeverlove/article/details/78813719&nbsp;</span></p>\n  <p><span>版权声明：本文为博主原创文章，转载请附上博文链接！</span></p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('10', '<html>\n <head></head>\n <body>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;在使用bootstrap的插件的时候，需要导入一些css、js。</p>\n  <pre style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; white-space: pre-wrap; overflow-wrap: break-word; font-family: &quot;Courier New&quot; !important;\">1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;link&nbsp;href=\"/static/css/bootstrap.min.css\"&nbsp;rel=\"stylesheet\"&gt;2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;script&nbsp;src=\"/static/js/jquery-2.1.1.min.js\"&gt;&lt;/script&gt;3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;script&nbsp;src=\"/static/js/bootstrap.min.js\"&gt;&lt;/script&gt;4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;script&nbsp;src=\"/static/js/bootstrap-paginator.js\"&gt;&lt;/script&gt;</pre>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;注意：js的导入顺序，jquery优先导入并且版本要2.x。中文乱码：&lt;<span style=\"margin: 0px; padding: 0px; line-height: 1.5; color: rgb(0, 0, 128); font-weight: bold;\">meta&nbsp;<span style=\"margin: 0px; padding: 0px; line-height: 1.5; color: rgb(0, 0, 255);\">http-equiv=<span style=\"margin: 0px; padding: 0px; line-height: 1.5; color: rgb(0, 128, 0);\">\"Content-Type\"&nbsp;<span style=\"margin: 0px; padding: 0px; line-height: 1.5; color: rgb(0, 0, 255);\">content=<span style=\"margin: 0px; padding: 0px; line-height: 1.5; color: rgb(0, 128, 0);\">\"text/html; charset=UTF-8\"&gt;</span></span></span></span></span></p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">其中：<span style=\"margin: 0px; padding: 0px; line-height: 1.5; color: rgb(0, 128, 0); font-weight: bold;\">bootstrap-paginator.js</span>下载的地址：https://github.com/lyonlai/bootstrap-paginator&nbsp; 在GitHub上进行下载。</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">html代码：</p>\n  <pre style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; white-space: pre-wrap; overflow-wrap: break-word; font-family: &quot;Courier New&quot; !important;\">1&nbsp;&lt;div&nbsp;id=\"example\"&nbsp;style=\"text-align:&nbsp;center\"&gt;&nbsp;&lt;ul&nbsp;id=\"pageLimit\"&gt;&lt;/ul&gt;&nbsp;&lt;/div&gt;</pre>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">js代码：</p>\n  <p>按 Ctrl+C 复制代码</p>\n  <p><textarea style=\"margin: 0px; padding: 0px; width: 1497px; height: 323.2px; font-family: &quot;Courier New&quot;; font-size: 12px; line-height: 1.5;\"></textarea></p>\n  <p>按 Ctrl+C 复制代码</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">效果：</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\"><img src=\"https://images2015.cnblogs.com/blog/959846/201702/959846-20170213132313332-23161744.png\" alt=\"\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 900px; height: auto;\"></p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">点击不同的页显示高亮蓝。</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;让我们一起看下bootstrap的文档介绍：</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\"><img src=\"https://images2015.cnblogs.com/blog/959846/201702/959846-20170213132649410-1258012029.png\" alt=\"\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 900px; height: auto;\"></p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\"><img src=\"https://images2015.cnblogs.com/blog/959846/201702/959846-20170213132732050-1053172761.png\" alt=\"\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 900px; height: auto;\"></p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">咱们在引用这个插件的时候需要做以下操作：</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">后端需要传来数据的可以进行分割多少个页：</p>\n  <p><span class=\"cnblogs_code_copy\" style=\"margin: 0px; padding: 0px 5px 0px 0px; line-height: 1.5;\"><a title=\"复制代码\" style=\"margin: 0px; padding: 0px; color: rgb(7, 93, 179); text-decoration-line: underline; border: none !important;\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\" style=\"margin: 0px; padding: 0px; max-width: 900px; height: auto; border: none !important;\"></a></span></p>\n  <pre style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; white-space: pre-wrap; overflow-wrap: break-word; font-family: &quot;Courier New&quot; !important;\">&nbsp;1&nbsp;def&nbsp;task_list_page(request):&nbsp;2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\'\'\'&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;功能：该函数主要是起到分页的作用。&nbsp;4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:param&nbsp;request:&nbsp;用户请求的对象，&nbsp;5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:return:&nbsp;返回前端数据或者页数。&nbsp;6&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\'\'\'&nbsp;7&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_dic={\'page_content\':None,\'page_count\':None}&nbsp;8&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if&nbsp;request.method==\'POST\':&nbsp;9&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_num=request.POST.get(\'page\',None)10&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;data_count=request.POST.get(\'count\',None)11&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;print(page_num,data_count)12&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;#&nbsp;pagesplit_obj=pagesplit.Pager(page_num)13&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_end=int(page_num)*int(data_count)14&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_start=page_end&nbsp;-int(data_count)15&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;query_obj=models.Task_info.objects.all()[page_start:page_end]16&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_count=models.Task_info.objects.count()17&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_cont_str=\'\'18&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for&nbsp;i&nbsp;in&nbsp;query_obj:19&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_cont_str+=\'\'\'20&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;tr&gt;21&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;td&gt;%s&lt;/td&gt;22&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;td&gt;%s&lt;/td&gt;23&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;td&gt;%s&lt;/td&gt;24&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;td&gt;&lt;a&nbsp;href=\"/res_query_check/%s\"&gt;点击查看任务结果&lt;/a&gt;&lt;/td&gt;&nbsp;&lt;/tr&gt;25&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\'\'\'%(i.id,i.task_name,i.task_user,i.id)26&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_dic[\'page_content\']=page_cont_str27&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_dic[\'page_count\']=page_count28&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;&nbsp;HttpResponse(json.dumps(page_dic))29&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;elif&nbsp;request.method==\'GET\':30&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_count&nbsp;=&nbsp;models.Task_info.objects.count()31&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;x,y=divmod(page_count,12)32&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if&nbsp;y:33&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_num=x+134&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;else:35&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;page_num=x36&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;render(request,\'task/task_list.html\',{\'pagecount\':page_num})#传递数据一共分多少页。</pre>\n  <p><span class=\"cnblogs_code_copy\" style=\"margin: 0px; padding: 0px 5px 0px 0px; line-height: 1.5;\"><a title=\"复制代码\" style=\"margin: 0px; padding: 0px; color: rgb(7, 93, 179); text-decoration-line: underline; border: none !important;\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\" style=\"margin: 0px; padding: 0px; max-width: 900px; height: auto; border: none !important;\"></a></span></p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">前端js显示页数：</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">自执行，请求默认第一页数据：</p>\n  <p><span class=\"cnblogs_code_copy\" style=\"margin: 0px; padding: 0px 5px 0px 0px; line-height: 1.5;\"><a title=\"复制代码\" style=\"margin: 0px; padding: 0px; color: rgb(7, 93, 179); text-decoration-line: underline; border: none !important;\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\" style=\"margin: 0px; padding: 0px; max-width: 900px; height: auto; border: none !important;\"></a></span></p>\n  <pre style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; white-space: pre-wrap; overflow-wrap: break-word; font-family: &quot;Courier New&quot; !important;\">&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(function&nbsp;()&nbsp;{&nbsp;2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$.ajax(&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{&nbsp;4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;url:\'/task_list_page/\',&nbsp;5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type:\'POST\',&nbsp;6&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;data:{\'page\':1,\'count\':12},&nbsp;7&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dataType:\'JSON\',&nbsp;8&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;success:function&nbsp;(callback)&nbsp;{&nbsp;9&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;var&nbsp;page_count=callback.page_count;10&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;var&nbsp;page_cont=callback.page_content;11&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(\'tbody\').append(page_cont);12&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(\'#last_page\').text(page_count)13&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}14&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}15&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)16&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;});</pre>\n  <p><span class=\"cnblogs_code_copy\" style=\"margin: 0px; padding: 0px 5px 0px 0px; line-height: 1.5;\"><a title=\"复制代码\" style=\"margin: 0px; padding: 0px; color: rgb(7, 93, 179); text-decoration-line: underline; border: none !important;\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\" style=\"margin: 0px; padding: 0px; max-width: 900px; height: auto; border: none !important;\"></a></span></p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; color: rgb(255, 0, 0);\">&nbsp;注意：$(\'#last_page\').text(page_count)使用的是：id为：last_page</span></p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">前端代码：</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">数据：</p>\n  <p><span class=\"cnblogs_code_copy\" style=\"margin: 0px; padding: 0px 5px 0px 0px; line-height: 1.5;\"><a title=\"复制代码\" style=\"margin: 0px; padding: 0px; color: rgb(7, 93, 179); text-decoration-line: underline; border: none !important;\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\" style=\"margin: 0px; padding: 0px; max-width: 900px; height: auto; border: none !important;\"></a></span></p>\n  <pre style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; white-space: pre-wrap; overflow-wrap: break-word; font-family: &quot;Courier New&quot; !important;\">&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;table&nbsp;class=\"table\"&gt;&nbsp;2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;thead&gt;&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;tr&gt;&nbsp;4&nbsp;{#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;th&nbsp;class=\"text-center\"&gt;#&lt;/th&gt;#}&nbsp;5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;th&gt;任务ID&lt;/th&gt;&nbsp;6&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;th&gt;任务名称&lt;/th&gt;&nbsp;7&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;th&gt;执行用户&lt;/th&gt;&nbsp;8&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;th&gt;执行结果&lt;/th&gt;&nbsp;9&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/tr&gt;10&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/thead&gt;11&nbsp;12&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;tbody&gt;13&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/tbody&gt;14&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/table&gt;</pre>\n  <p><span class=\"cnblogs_code_copy\" style=\"margin: 0px; padding: 0px 5px 0px 0px; line-height: 1.5;\"><a title=\"复制代码\" style=\"margin: 0px; padding: 0px; color: rgb(7, 93, 179); text-decoration-line: underline; border: none !important;\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\" style=\"margin: 0px; padding: 0px; max-width: 900px; height: auto; border: none !important;\"></a></span></p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;分页：</p>\n  <pre style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; white-space: pre-wrap; overflow-wrap: break-word; font-family: &quot;Courier New&quot; !important;\">1&nbsp;&nbsp;&lt;div&nbsp;id=\"example\"&nbsp;style=\"text-align:&nbsp;center\"&gt;&nbsp;&lt;ul&nbsp;id=\"pageLimit\"&gt;&lt;/ul&gt;&nbsp;&lt;/div&gt;</pre>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;js代码：</p>\n  <p><span class=\"cnblogs_code_copy\" style=\"margin: 0px; padding: 0px 5px 0px 0px; line-height: 1.5;\"><a title=\"复制代码\" style=\"margin: 0px; padding: 0px; color: rgb(7, 93, 179); text-decoration-line: underline; border: none !important;\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\" style=\"margin: 0px; padding: 0px; max-width: 900px; height: auto; border: none !important;\"></a></span></p>\n  <pre style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; white-space: pre-wrap; overflow-wrap: break-word; font-family: &quot;Courier New&quot; !important;\">&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(\'#pageLimit\').bootstrapPaginator({&nbsp;2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;currentPage:&nbsp;1,&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;totalPages:&nbsp;{{&nbsp;pagecount&nbsp;}},&nbsp;4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;size:\"normal\",&nbsp;5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bootstrapMajorVersion:&nbsp;3,&nbsp;6&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;alignment:\"right\",&nbsp;7&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;numberOfPages:8,&nbsp;8&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;itemTexts:&nbsp;function&nbsp;(type,&nbsp;page,&nbsp;current)&nbsp;{&nbsp;9&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;switch&nbsp;(type)&nbsp;{10&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;case&nbsp;\"first\":&nbsp;return&nbsp;\"首页\";11&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;case&nbsp;\"prev\":&nbsp;return&nbsp;\"上一页\";12&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;case&nbsp;\"next\":&nbsp;return&nbsp;\"下一页\";13&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;case&nbsp;\"last\":&nbsp;return&nbsp;\"末页\";14&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;case&nbsp;\"page\":&nbsp;return&nbsp;page;15&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}//默认显示的是第一页。16&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},17&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;onPageClicked:&nbsp;function&nbsp;(event,&nbsp;originalEvent,&nbsp;type,&nbsp;page){//给每个页眉绑定一个事件，其实就是ajax请求，其中page变量为当前点击的页上的数字。18&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$.ajax({19&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;url:\'/task_list_page/\',20&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type:\'POST\',21&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;data:{\'page\':page,\'count\':12},22&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dataType:\'JSON\',23&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;success:function&nbsp;(callback)&nbsp;{24&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(\'tbody\').empty();25&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;var&nbsp;page_count=callback.page_count;26&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;var&nbsp;page_cont=callback.page_content;27&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(\'tbody\').append(page_cont);28&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(\'#last_page\').text(page_count)29&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}30&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;})31&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}32&nbsp;});</pre>\n  <p><span class=\"cnblogs_code_copy\" style=\"margin: 0px; padding: 0px 5px 0px 0px; line-height: 1.5;\"><a title=\"复制代码\" style=\"margin: 0px; padding: 0px; color: rgb(7, 93, 179); text-decoration-line: underline; border: none !important;\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\" style=\"margin: 0px; padding: 0px; max-width: 900px; height: auto; border: none !important;\"></a></span></p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&nbsp;</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">效果：</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\"><img src=\"https://images2015.cnblogs.com/blog/959846/201702/959846-20170213134322457-1467654290.png\" alt=\"\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 900px; height: auto;\"></p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('11', '<html>\n <head></head>\n <body>\n  <p style=\"box-sizing: border-box; outline: 0px; margin-top: 0px; margin-bottom: 16px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, &quot;SF Pro Display&quot;, Roboto, Noto, Arial, &quot;PingFang SC&quot;, sans-serif; color: rgb(79, 79, 79); line-height: 26px; overflow-x: auto; overflow-wrap: break-word; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"box-sizing: border-box; outline: 0px; margin: 0px; padding: 0px; font-weight: 700; overflow-wrap: break-word;\">1. freemarker获取list的size ：</span>&nbsp;</p>\n  <pre class=\"has\" name=\"code\" style=\"box-sizing: border-box; outline: 0px; margin-top: 0px; margin-bottom: 24px; padding: 8px; position: relative; font-family: Consolas, Inconsolata, Courier, monospace; white-space: pre-wrap; overflow-wrap: break-word; overflow-x: auto; font-size: 14px; line-height: 22px; background-color: rgb(255, 255, 255);\">JavaArrayList&lt;String&gt;&nbsp;list&nbsp;=&nbsp;new&nbsp;ArrayList&lt;String&gt;();Freemaker${list?size}</pre>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('12', '<html>\n <head></head>\n <body>\n  <p><br></p>\n  <h1 class=\"postTitle\" style=\"margin: 0px; padding: 0px 0px 0px 5px; float: left; line-height: 1.5; width: 1516.11px; clear: both; font-size: 14px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\"><a id=\"cb_post_title_url\" class=\"postTitle2\" href=\"https://www.cnblogs.com/ljhoracle/p/3859976.html\" style=\"margin: 0px; padding: 0px; color: rgb(7, 93, 179); text-decoration-line: none;\">关于在freemarker模板中遍历数据模型List&lt;JavaBean&gt;的经验</a></h1>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5;\">&nbsp; &nbsp; &nbsp; &nbsp;本文采用简单的servlet作为后台处理数据的工具，前台使用freemarker的ftl模板作为输出工具，简单说明怎样将封装有实体类对象的List集合注入到ftl模板中并且成功的在遍历显示出来，之前在网上找了很多这方面的资料，但是都没有解决这个问题，所以自己就从头认真的研读的一番freemarker的API文档，阅读了相关的类和接口的功能说明，终于找到了突破口，在这里写出来供和我有相同经历的孩纸(初学者)使用：</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5;\">首先看我写的domain实体类：News.java</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5;\">public class News {<br style=\"margin: 0px; padding: 0px;\">private Integer news_id;<br style=\"margin: 0px; padding: 0px;\">private String news_title;<br style=\"margin: 0px; padding: 0px;\">private String news_publish_date;<br style=\"margin: 0px; padding: 0px;\">private String news_url;<br style=\"margin: 0px; padding: 0px;\">public Integer getNews_id() {<br style=\"margin: 0px; padding: 0px;\">return news_id;<br style=\"margin: 0px; padding: 0px;\">}<br style=\"margin: 0px; padding: 0px;\">public void setNews_id(Integer news_id) {<br style=\"margin: 0px; padding: 0px;\">this.news_id = news_id;<br style=\"margin: 0px; padding: 0px;\">}</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5;\">.....以下的set和get方法都省略..</p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('13', '<html>\n <head></head>\n <body>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">接着看我写的newsSql.xml文件中查询所有News对象的并返回List&lt;News&gt;或者Map&lt;String,News&gt;型数据的配置信息：</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">&lt;typeAlias alias=\"news\" type=\"cn.domain.News\"/&gt;<br style=\"margin: 0px; padding: 0px;\">&lt;resultMap class=\"news\" id=\"getMap\"&gt;<br style=\"margin: 0px; padding: 0px;\">&lt;result property=\"news_id\" column=\"news_id\"/&gt;<br style=\"margin: 0px; padding: 0px;\">&lt;result property=\"news_title\" column=\"news_title\"/&gt;<br style=\"margin: 0px; padding: 0px;\">&lt;result property=\"news_publish_date\" column=\"news_publish_date\"/&gt;<br style=\"margin: 0px; padding: 0px;\">&lt;result property=\"news_url\" column=\"news_url\"/&gt;<br style=\"margin: 0px; padding: 0px;\">&lt;/resultMap&gt;<br style=\"margin: 0px; padding: 0px;\">&lt;select id=\"queryAllNews\" resultMap=\"getMap\"&gt;<br style=\"margin: 0px; padding: 0px;\">select *from news<br style=\"margin: 0px; padding: 0px;\">&lt;/select&gt;</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">下面是自己第一次写的NewsListServlet代码：</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">public class NewsListServlet extends HttpServlet {</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">private static final long serialVersionUID = 1L;<br style=\"margin: 0px; padding: 0px;\">private NewsDao dao;<br style=\"margin: 0px; padding: 0px;\">private Configuration cfg;</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">public void doGet(HttpServletRequest request, HttpServletResponse response)<br style=\"margin: 0px; padding: 0px;\">throws ServletException, IOException {<br style=\"margin: 0px; padding: 0px;\">request.setCharacterEncoding(\"utf-8\");<br style=\"margin: 0px; padding: 0px;\">response.setContentType(\"text/html;charset=utf-8\");<br style=\"margin: 0px; padding: 0px;\">dao = new NewsDao();<br style=\"margin: 0px; padding: 0px;\">List&lt;News&gt; newslist = dao.getAllNews();<br style=\"margin: 0px; padding: 0px;\">Map&lt;String,List&lt;News&gt;&gt; map=new HashMap&lt;String, List&lt;News&gt;&gt;();<br style=\"margin: 0px; padding: 0px;\">map.put(\"newslist\",newslist);<br style=\"margin: 0px; padding: 0px;\">cfg = new Configuration();<br style=\"margin: 0px; padding: 0px;\">cfg.setServletContextForTemplateLoading(this.getServletContext(),\"/ftl\");<br style=\"margin: 0px; padding: 0px;\">Template template = cfg.getTemplate(\"newsList.ftl\");<br style=\"margin: 0px; padding: 0px;\">try {<br style=\"margin: 0px; padding: 0px;\">template.process(map,response.getWriter());<br style=\"margin: 0px; padding: 0px;\">} catch (TemplateException e) {<br style=\"margin: 0px; padding: 0px;\">e.printStackTrace();<br style=\"margin: 0px; padding: 0px;\">}</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">}}</p>\n  <p style=\"margin: 10px auto; padding: 0px; line-height: 1.5; font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(254, 254, 242);\">下面是newslist.ftl模板代码：</p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('14', '<html>\n <head></head>\n <body>\n  <p><img src=\"/accessory/admin/article_20190508182207661.gif\" title=\"\" alt=\"\"></p>\n  <p>来吗？？？</p>\n  <p><img src=\"/accessory/admin/article_20190508182218978.jpg\" title=\"\" alt=\"\" width=\"169\" height=\"337\" style=\"width: 169px; height: 337px;\"></p>\n  <p>5555555555<img src=\"/accessory/admin/article_20190508213711987.jpg\" title=\"\" alt=\"\"></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('15', '<html>\n <head></head>\n <body>\n  <p>123</p>\n  <p>123<img src=\"/accessory/admin/article_20190507210603753.gif\" title=\"\" alt=\"\"></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('16', '<html>\n <head></head>\n <body>\n  <h1 style=\"border: 0px solid red; padding: 10px 10px 10px 0px; font-size: 22px;\"><a id=\"cb_post_title_url\" class=\"postTitle2\" href=\"https://www.cnblogs.com/shihuibei/p/9249155.html\" style=\"border-bottom: 0px dotted rgb(239, 239, 239); padding: 3px; color: rgb(120, 175, 211); text-decoration-line: none; font-family: 微软雅黑, Consolas, Arial, 宋体;\">centos7 安装MySQL7 并更改初始化密码</a></h1>\n  <h1 id=\"1官方安装文档\" style=\"font-size: 25px; line-height: 1.5; margin: 24px 0px 12px; padding: 5px; color: white; background-color: rgb(165, 165, 165);\">1、官方安装文档</h1>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\"><a href=\"http://dev.mysql.com/doc/mysql-yum-repo-quick-guide/en/\" rel=\"nofollow\" target=\"_blank\" style=\"color: rgb(91, 157, 202); text-decoration-line: none; padding: 1px 2px;\">http://dev.mysql.com/doc/mysql-yum-repo-quick-guide/en/</a></p>\n  <h1 id=\"2下载-mysql-yum包\" style=\"font-size: 25px; line-height: 1.5; margin: 24px 0px 12px; padding: 5px; color: white; background-color: rgb(165, 165, 165);\">2、下载 Mysql yum包</h1>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\"><a href=\"http://dev.mysql.com/downloads/repo/yum/\" rel=\"nofollow\" target=\"_blank\" style=\"color: rgb(91, 157, 202); text-decoration-line: none; padding: 1px 2px;\">http://dev.mysql.com/downloads/repo/yum/</a></p>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\"><img title=\"\" src=\"https://upload-images.jianshu.io/upload_images/1342351-dbe0a0ebb51aa98c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"\" style=\"border: 0px; max-width: 900px; height: auto;\"></p>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">下载到本地再上传到服务器，或者使用wget 直接下载</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">wget&nbsp;http://repo.mysql.com/mysql57-community-release-el7-10.noarch.rpm</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">&nbsp;</p>\n  <h1 id=\"3安转软件源\" style=\"font-size: 25px; line-height: 1.5; margin: 24px 0px 12px; padding: 5px; color: white; background-color: rgb(165, 165, 165);\">3、安转软件源</h1>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">将<code>platform-and-version-specific-package-name</code>&nbsp;替换为你下载的rpm名</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">sudo&nbsp;rpm&nbsp;-Uvh&nbsp;platform-and-version-specific-package-name.rpm</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">例如</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">rpm&nbsp;-Uvh&nbsp;mysql57-community-release-el7-10.noarch.rpm</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\"><img title=\"\" src=\"https://upload-images.jianshu.io/upload_images/1342351-c62f0707f1100946.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"\" style=\"border: 0px; max-width: 900px; height: auto;\"></p>\n  <h1 id=\"4安装mysql服务端\" style=\"font-size: 25px; line-height: 1.5; margin: 24px 0px 12px; padding: 5px; color: white; background-color: rgb(165, 165, 165);\">4、安装mysql服务端</h1>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">&nbsp;yum&nbsp;install&nbsp;&nbsp;-y&nbsp;&nbsp;mysql-community-server</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">如果网络环境不是很好，执行完命令就可能会等待一些是时间</p>\n  <h1 id=\"5启动mysql\" style=\"font-size: 25px; line-height: 1.5; margin: 24px 0px 12px; padding: 5px; color: white; background-color: rgb(165, 165, 165);\">5、启动mysql</h1>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">service&nbsp;mysqld&nbsp;start</pre>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">systemctl&nbsp;start&nbsp;mysqld.service</pre>\n  <h1 id=\"6检查mysql-的运行状态\" style=\"font-size: 25px; line-height: 1.5; margin: 24px 0px 12px; padding: 5px; color: white; background-color: rgb(165, 165, 165);\">6、检查mysql 的运行状态</h1>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">service&nbsp;mysqld&nbsp;status</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">&nbsp;</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">systemctl&nbsp;status&nbsp;mysqld.service</pre>\n  <h1 id=\"7修改临时密码\" style=\"font-size: 25px; line-height: 1.5; margin: 24px 0px 12px; padding: 5px; color: white; background-color: rgb(165, 165, 165);\">7、修改临时密码</h1>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">Mysql5.7默认安装之后root是有密码的。</p>\n  <h4 id=\"71-获取mysql的临时密码\" style=\"font-size: 14px; margin: 10px 0px;\">7.1 获取MySQL的临时密码</h4>\n  <blockquote style=\"background-image: none; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; border: 2px solid rgb(239, 239, 239); padding: 5px 10px; margin-top: 10px; margin-bottom: 10px;\">\n   <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">为了加强安全性，MySQL5.7为root用户随机生成了一个密码，在error log中，关于error log的位置，如果安装的是RPM包，则默认是/var/log/mysqld.log。&nbsp;<br>只有启动过一次mysql才可以查看临时密码</p>\n  </blockquote>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">grep&nbsp;\'temporary&nbsp;password\'&nbsp;/var/log/mysqld.log</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\"><img title=\"\" src=\"https://upload-images.jianshu.io/upload_images/1342351-6670404dea32c709.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"\" style=\"border: 0px; max-width: 900px; height: auto;\">&nbsp;<br>这里的密码是<code>YdsGaxOq&gt;2n!</code></p>\n  <h4 id=\"72-登陆并修改密码\" style=\"font-size: 14px; margin: 10px 0px;\">7.2 登陆并修改密码</h4>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">使用默认的密码登陆</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">mysql&nbsp;-uroot&nbsp;-p</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">用该密码登录到服务端后，必须马上修改密码，不然会报如下错误：</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">mysql&gt;&nbsp;select&nbsp;@@log_error;\r\nERROR&nbsp;1820&nbsp;(HY000):&nbsp;You&nbsp;must&nbsp;reset&nbsp;your&nbsp;password&nbsp;using&nbsp;ALTER&nbsp;USER&nbsp;statement&nbsp;before&nbsp;executing&nbsp;this&nbsp;statement.\r\nmysql&gt;</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">修改密码</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">ALTER&nbsp;USER&nbsp;\'root\'@\'localhost\'&nbsp;IDENTIFIED&nbsp;BY&nbsp;\'root123\';</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">如果密码设置太简单出现以下的提示</p>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\"><img title=\"\" src=\"https://upload-images.jianshu.io/upload_images/1342351-2dff68de78dca1aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"\" style=\"border: 0px; max-width: 900px; height: auto;\"></p>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">如何解决<code>ERROR 1819 (HY000): Your password does not satisfy the current policy requirements</code>呢？ 这里直接提供解决方案文末有详细的说明</p>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">必须修改两个全局参数：&nbsp;<br>首先，修改validate_password_policy参数的值</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">mysql&gt;&nbsp;set&nbsp;global&nbsp;validate_password_policy=0;</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">再修改密码的长度</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">mysql&gt;&nbsp;set&nbsp;global&nbsp;validate_password_length=1;</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">再次执行修改密码就可以了</p>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">ALTER&nbsp;USER&nbsp;\'root\'@\'localhost\'&nbsp;IDENTIFIED&nbsp;BY&nbsp;\'root123\';</pre>\n  <p style=\"margin: 10px auto; line-height: 23.4px; padding: 0px;\">&nbsp;</p>\n  <h1 id=\"8授权其他机器登陆\" style=\"font-size: 25px; line-height: 1.5; margin: 24px 0px 12px; padding: 5px; color: white; background-color: rgb(165, 165, 165);\">8、授权其他机器登陆</h1>\n  <pre name=\"code\" class=\"prettyprint\" style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-wrap; overflow-wrap: break-word;\">GRANT&nbsp;ALL&nbsp;PRIVILEGES&nbsp;ON&nbsp;*.*&nbsp;TO&nbsp;\'root\'@\'%\'&nbsp;IDENTIFIED&nbsp;BY&nbsp;\'mypassword\'&nbsp;WITH&nbsp;GRANT&nbsp;OPTION;FLUSH&nbsp;&nbsp;PRIVILEGES;</pre>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('17', '<html>\n <head></head>\n <body>\n  <p>123123</p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('18', '<html>\n <head></head>\n <body>\n  <p>dasdas</p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('19', '<html>\n <head></head>\n <body>\n  <p>dsadsa</p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('20', '<html>\n <head></head>\n <body>\n  <p>dsadasdsadsadasdasdas<br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('21', '<html>\n <head></head>\n <body>\n  <p style=\"box-sizing: inherit;line-height: inherit;margin: 10px auto;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-weight: bolder\">Maven 构建项目</span></p>\n  <ul style=\"box-sizing: inherit;font-size: 21.3333px;line-height: inherit;margin-left: 30px;padding: 10px 0px 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\" class=\" list-paddingleft-2\">\n   <li><p>1、访问 http://start.spring.io/</p></li>\n   <li><p>2、选择构建工具 Maven Project、Java、Spring Boot 版本 2.1.3 以及一些工程基本信息，可参考下图所示：</p></li>\n  </ul>\n  <p style=\"box-sizing: inherit;line-height: inherit;margin: 10px auto;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\"><img src=\"http://www.itmind.net/assets/images/2019/springboot/spring-boot-start.png\" alt=\"\" style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border: 0px;background-repeat: no-repeat;vertical-align: middle;max-width: 70%;height: auto\"></p>\n  <ul style=\"box-sizing: inherit;font-size: 21.3333px;line-height: inherit;margin-left: 30px;padding: 10px 0px 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\" class=\" list-paddingleft-2\">\n   <li><p>3、点击 Generate Project 下载项目压缩包</p></li>\n   <li><p>4、解压后，使用 Idea 导入项目，File -&gt; New -&gt; Model from Existing Source.. -&gt; 选择解压后的文件夹 -&gt; OK，选择 Maven 一路 Next，OK done!</p></li>\n   <li><p>5、如果使用的是 Eclipse，Import -&gt; Existing Maven Projects -&gt; Next -&gt; 选择解压后的文件夹 -&gt; Finsh，OK done!</p></li>\n  </ul>\n  <p style=\"box-sizing: inherit;line-height: inherit;margin: 10px auto;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-weight: bolder\">Idea 构建项目</span></p>\n  <ul style=\"box-sizing: inherit;font-size: 21.3333px;line-height: inherit;margin-left: 30px;padding: 10px 0px 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\" class=\" list-paddingleft-2\">\n   <li><p>1、选择 File -&gt; New —&gt; Project... 弹出新建项目的框</p></li>\n   <li><p>2、选择 Spring Initializr，Next 也会出现上述类似的配置界面，Idea 帮我们做了集成</p></li>\n   <li><p>3、填写相关内容后，点击 Next 选择依赖的包再点击 Next，最后确定信息无误点击 Finish。</p></li>\n  </ul>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px auto;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\">&nbsp;</p>\n  <p style=\"box-sizing: inherit;line-height: inherit;margin: 10px auto;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-weight: bolder\"><span style=\"box-sizing: inherit;font-size: 19px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: 仿宋\">项目结构介绍</span></span></p>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px auto;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255);text-align: center\"><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: 仿宋;color: rgb(51, 51, 51);letter-spacing: 0\"><a href=\"http://images2015.cnblogs.com/blog/331425/201607/331425-20160712105137014-669859839.png\" style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;color: rgb(36, 132, 193)\"><img title=\"springboot2\" src=\"https://images2015.cnblogs.com/blog/331425/201607/331425-20160712105138029-1564953731.png\" alt=\"springboot2\" width=\"803\" height=\"417\" border=\"0\" style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;margin: 1px 0px;padding: 0px;border: 0px;background-repeat: no-repeat;vertical-align: middle;max-width: 70%;height: auto;background-image: none;display: inline\"></a></span></p>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px auto;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\">&nbsp;</p>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px auto;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: 仿宋;color: rgb(51, 51, 51);letter-spacing: 0\">如上图所示，Spring Boot的基础结构共三个文件</span><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: 仿宋;color: rgb(51, 51, 51);letter-spacing: 0\">:</span></p>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px auto 10px 28px;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: wingdings;color: rgb(51, 51, 51);letter-spacing: 0\"><span style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat\">l<span style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat\">&nbsp;</span></span></span><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: 仿宋;color: rgb(51, 51, 51);letter-spacing: 0\">src/main/java&nbsp; 程序开发以及主程序入口</span></p>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px auto 10px 28px;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: wingdings;color: rgb(51, 51, 51);letter-spacing: 0\"><span style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat\">l<span style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat\">&nbsp;</span></span></span><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: 仿宋;color: rgb(51, 51, 51);letter-spacing: 0\">src/main/resources 配置文件</span></p>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px auto 10px 28px;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: wingdings;color: rgb(51, 51, 51);letter-spacing: 0\"><span style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat\">l<span style=\"box-sizing: inherit;font-size: inherit;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat\">&nbsp;</span></span></span><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: 仿宋;color: rgb(51, 51, 51);letter-spacing: 0\">src/test/java&nbsp; 测试程序</span></p>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px auto;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255);text-align: justify\">&nbsp;</p>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px 0;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;text-indent: 0;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255);text-align: justify\"><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: 仿宋;color: rgb(51, 51, 51);letter-spacing: 0\">另外，spingboot建议的目录结果如下：</span></p>\n  <p style=\"box-sizing: inherit;line-height: 24px;margin: 10px 0;padding: 0px;border-style: solid;border-width: 0px;background-repeat: no-repeat;text-indent: 0;font-family: Georgia, \'Times New Roman\', Times, sans-serif;white-space: normal;background-color: rgb(255, 255, 255);text-align: justify\"><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;font-family: 仿宋;color: rgb(51, 51, 51);letter-spacing: 0\">root package结构：</span><span style=\"box-sizing: inherit;font-size: 16px;line-height: inherit;padding: 0px;border-style: solid;border-width: 0px;background: rgb(217, 217, 217);font-family: 仿宋;color: rgb(51, 51, 51);letter-spacing: 0\">com.example.myproject</span></p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('22', '<html>\n <head></head>\n <body>\n  <p><span>当使用Springboot自带上传时，超出最大上传限制后会返回500.</span></p>\n  <p><span><br></span></p>\n  <p><span>第一种处理方式，（没有成功，但能捕获到异常，无法正常显示输出信息）：</span></p>\n  <p><span><br></span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span>通过异常捕获，问题：没有正常返回指定信息。</span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span>@RestControllerAdvice</span></p>\n  <p><span>public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {</span></p>\n  <p><span>&nbsp;private static final Logger logger =&nbsp;</span></p>\n  <p><span>&nbsp; &nbsp; LoggerFactory.getLogger(GlobalExceptionHandler.class);</span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span>&nbsp;/**</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp;* 处理上传异常</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp;* @param t</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp;* @return</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp;*/</span></p>\n  <p><span>&nbsp; &nbsp; @ExceptionHandler(MultipartException.class)</span></p>\n  <p><span>&nbsp; &nbsp; public ResponseEntity&lt;Result&gt; handleAll(Throwable t) throws Exception {</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp; &nbsp; // TODO do Throwable t</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp; &nbsp; logger.error(\"=&gt;\"+t.getMessage());</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp; &nbsp; HttpHeaders headers = new HttpHeaders();</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp; &nbsp; headers.set(\"Content-Type\",\"application/json;charset=UTF-8\");</span></p>\n  <p><span>&nbsp; &nbsp; &nbsp; &nbsp; return new ResponseEntity&lt;&gt;(new Result(-1,\"上传文件异常！\",null),headers, HttpStatus.OK);</span></p>\n  <p><span>&nbsp; &nbsp; }</span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span>&nbsp;</span></p>\n  <p><span>｝</span></p>\n  <p><br></p>\n  <p><br></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('23', '<html>\n <head></head>\n <body>\n  <p><img src=\"/accessory/admin/article_20190606112007688.png\" title=\"\" alt=\"\"></p>\n  <p><br></p>\n  <p><img src=\"/accessory/admin/article_20190606112007688.png\" title=\"\" alt=\"\"></p>\n  <p><br></p>\n  <p></p>\n </body>\n</html>');
INSERT INTO `t_article_content` VALUES ('24', '<html>\n <head></head>\n <body>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 0px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\"><strong>前言</strong></p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\"><strong>&nbsp;&nbsp;&nbsp;&nbsp;Java码农不识Apache，敲尽一生也枉然。</strong>旗下的开源项目众多，各个都是吊炸天。今日且说Commons,轻轻点击此链接进入Apache&nbsp;Commons主页，Logging、Pool、Net、ONGL、EL、IO、DBCP、Email、Collection、Lang……等等项目中常用到的包。而这篇文章的主角Lang则是我们最常用的工具作为jdk的补充，怎能不去详细探究一番！</p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\"><strong pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\">一、依赖：</strong></p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\"><strong pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\"></strong></p>\n  <pre courier=\"\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 9.5px; border-radius: 4px; background-color: rgb(245, 245, 245); box-sizing: border-box; overflow: auto; font-size: 13px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; overflow-wrap: break-word; border: 1px solid rgb(204, 204, 204); white-space: pre-wrap;\">&lt;dependency&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;groupId&gt;org.apache.commons&lt;/groupId&gt;\r\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;artifactId&gt;commons-lang3&lt;/artifactId&gt;\r\n&lt;/dependency&gt;</pre>\n  <p style=\"white-space: normal;\">&nbsp;</p>\n  <p style=\"white-space: normal;\">&nbsp; &nbsp;</p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\"><strong>二、字符串的处理类（StringUtils）</strong></p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\">&nbsp;&nbsp;&nbsp;&nbsp;org.apache.commons.lang3.StringUtils 继承Object,Operations on&nbsp;String&nbsp;that are&nbsp;null&nbsp;safe。所谓的null safe就是对String进行操作不会出现NullPointerException异常，很实用有没有！以后再也不怕到处出现空指针异常了。先看看官方文档中这个类都有些什么方法：</p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\">&nbsp;&nbsp;&nbsp;&nbsp;这些方法基本上看方法名，就能猜出它大概的作用了。</p>\n  <pre courier=\"\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 9.5px; border-radius: 4px; background-color: rgb(245, 245, 245); box-sizing: border-box; overflow: auto; font-size: 13px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; overflow-wrap: break-word; border: 1px solid rgb(204, 204, 204); white-space: pre-wrap;\">//缩短到某长度,用...结尾.其实就是(substring(str,&nbsp;0,&nbsp;max-3)&nbsp;+&nbsp;\"...\")\r\n//public&nbsp;static&nbsp;String&nbsp;abbreviate(String&nbsp;str,int&nbsp;maxWidth)\r\nStringUtils.abbreviate(\"abcdefg\",&nbsp;6);//&nbsp;---\"abc...\"\r\n\r\n//字符串结尾的后缀是否与你要结尾的后缀匹配，若不匹配则添加后缀\r\nStringUtils.appendIfMissing(\"abc\",\"xyz\");//---\"abcxyz\"\r\nStringUtils.appendIfMissingIgnoreCase(\"abcXYZ\",\"xyz\");//---\"abcXYZ\"\r\n\r\n//首字母大小写转换\r\nStringUtils.capitalize(\"cat\");//---\"Cat\"\r\nStringUtils.uncapitalize(\"Cat\");//---\"cat\"\r\n\r\n//字符串扩充至指定大小且居中（若扩充大小少于原字符大小则返回原字符，若扩充大小为&nbsp;负数则为0计算&nbsp;）\r\nStringUtils.center(\"abcd\",&nbsp;2);//---&nbsp;\"abcd\"\r\nStringUtils.center(\"ab\",&nbsp;-1);//---&nbsp;\"ab\"\r\nStringUtils.center(\"ab\",&nbsp;4);//---\"&nbsp;ab&nbsp;\"\r\nStringUtils.center(\"a\",&nbsp;4,&nbsp;\"yz\");//---\"yayz\"\r\nStringUtils.center(\"abc\",&nbsp;7,&nbsp;\"\");//---\"&nbsp;&nbsp;abc&nbsp;&nbsp;\"\r\n\r\n//去除字符串中的\"\\n\",&nbsp;\"\\r\",&nbsp;or&nbsp;\"\\r\\n\"\r\nStringUtils.chomp(\"abc\\r\\n\");//---\"abc\"\r\n\r\n//判断一字符串是否包含另一字符串\r\nStringUtils.contains(\"abc\",&nbsp;\"z\");//---false\r\nStringUtils.containsIgnoreCase(\"abc\",&nbsp;\"A\");//---true\r\n\r\n//统计一字符串在另一字符串中出现次数\r\nStringUtils.countMatches(\"abba\",&nbsp;\"a\");//---2\r\n\r\n//删除字符串中的梭有空格\r\nStringUtils.deleteWhitespace(\"&nbsp;&nbsp;&nbsp;ab&nbsp;&nbsp;c&nbsp;&nbsp;\");//---\"abc\"\r\n\r\n//比较两字符串，返回不同之处。确切的说是返回第二个参数中与第一个参数所不同的字符串\r\nStringUtils.difference(\"abcde\",&nbsp;\"abxyz\");//---\"xyz\"\r\n\r\n//检查字符串结尾后缀是否匹配\r\nStringUtils.endsWith(\"abcdef\",&nbsp;\"def\");//---true\r\nStringUtils.endsWithIgnoreCase(\"ABCDEF\",&nbsp;\"def\");//---true\r\nStringUtils.endsWithAny(\"abcxyz\",&nbsp;new&nbsp;String[]&nbsp;{null,&nbsp;\"xyz\",&nbsp;\"abc\"});//---true\r\n\r\n//检查起始字符串是否匹配\r\nStringUtils.startsWith(\"abcdef\",&nbsp;\"abc\");//---true\r\nStringUtils.startsWithIgnoreCase(\"ABCDEF\",&nbsp;\"abc\");//---true\r\nStringUtils.startsWithAny(\"abcxyz\",&nbsp;new&nbsp;String[]&nbsp;{null,&nbsp;\"xyz\",&nbsp;\"abc\"});//---true\r\n\r\n//判断两字符串是否相同\r\nStringUtils.equals(\"abc\",&nbsp;\"abc\");//---true\r\nStringUtils.equalsIgnoreCase(\"abc\",&nbsp;\"ABC\");//---true\r\n\r\n//比较字符串数组内的所有元素的字符序列，起始一致则返回一致的字符串，若无则返回\"\"\r\nStringUtils.getCommonPrefix(new&nbsp;String[]&nbsp;{\"abcde\",&nbsp;\"abxyz\"});//---\"ab\"\r\n\r\n//正向查找字符在字符串中第一次出现的位置\r\nStringUtils.indexOf(\"aabaabaa\",&nbsp;\"b\");//---2\r\nStringUtils.indexOf(\"aabaabaa\",&nbsp;\"b\",&nbsp;3);//---5(从角标3后查找)\r\nStringUtils.ordinalIndexOf(\"aabaabaa\",&nbsp;\"a\",&nbsp;3);//---1(查找第n次出现的位置)\r\n\r\n//反向查找字符串第一次出现的位置\r\nStringUtils.lastIndexOf(\"aabaabaa\",&nbsp;\'b\');//---5\r\nStringUtils.lastIndexOf(\"aabaabaa\",&nbsp;\'b\',&nbsp;4);//---2\r\nStringUtils.lastOrdinalIndexOf(\"aabaabaa\",&nbsp;\"ab\",&nbsp;2);//---1\r\n\r\n//判断字符串大写、小写\r\nStringUtils.isAllUpperCase(\"ABC\");//---true\r\nStringUtils.isAllLowerCase(\"abC\");//---false\r\n\r\n//判断是否为空(注：isBlank与isEmpty&nbsp;区别)\r\nStringUtils.isBlank(null);StringUtils.isBlank(\"\");StringUtils.isBlank(\"&nbsp;\");//---true\r\nStringUtils.isNoneBlank(\"&nbsp;\",&nbsp;\"bar\");//---false\r\n&nbsp;&nbsp;\r\nStringUtils.isEmpty(null);StringUtils.isEmpty(\"\");//---true\r\nStringUtils.isEmpty(\"&nbsp;\");//---false\r\nStringUtils.isNoneEmpty(\"&nbsp;\",&nbsp;\"bar\");//---true\r\n\r\n//判断字符串数字\r\nStringUtils.isNumeric(\"123\");//---false\r\nStringUtils.isNumeric(\"12&nbsp;3\");//---false&nbsp;(不识别运算符号、小数点、空格……)\r\nStringUtils.isNumericSpace(\"12&nbsp;3\");//---true\r\n\r\n//数组中加入分隔符号\r\n//StringUtils.join([1,&nbsp;2,&nbsp;3],&nbsp;\';\');//---\"1;2;3\"\r\n\r\n//大小写转换\r\nStringUtils.upperCase(\"aBc\");//---\"ABC\"\r\nStringUtils.lowerCase(\"aBc\");//---\"abc\"\r\nStringUtils.swapCase(\"The&nbsp;dog&nbsp;has&nbsp;a&nbsp;BONE\");//---\"tHE&nbsp;DOG&nbsp;HAS&nbsp;A&nbsp;bone\"\r\n\r\n//替换字符串内容……（replacePattern、replceOnce）\r\nStringUtils.replace(\"aba\",&nbsp;\"a\",&nbsp;\"z\");//---\"zbz\"\r\nStringUtils.overlay(\"abcdef\",&nbsp;\"zz\",&nbsp;2,&nbsp;4);//---\"abzzef\"(指定区域)\r\nStringUtils.replaceEach(\"abcde\",&nbsp;new&nbsp;String[]{\"ab\",&nbsp;\"d\"},\r\n&nbsp;&nbsp;&nbsp;&nbsp;new&nbsp;String[]{\"w\",&nbsp;\"t\"});//---\"wcte\"(多组指定替换ab-&gt;w，d-&gt;t)\r\n\r\n//重复字符\r\nStringUtils.repeat(\'e\',&nbsp;3);//---\"eee\"\r\n\r\n//反转字符串\r\nStringUtils.reverse(\"bat\");//---\"tab\"\r\n\r\n//删除某字符\r\nStringUtils.remove(\"queued\",&nbsp;\'u\');//---\"qeed\"\r\n\r\n//分割字符串\r\nStringUtils.split(\"a..b.c\",&nbsp;\'.\');//---[\"a\",&nbsp;\"b\",&nbsp;\"c\"]\r\nStringUtils.split(\"ab:cd:ef\",&nbsp;\":\",&nbsp;2);//---[\"ab\",&nbsp;\"cd:ef\"]\r\nStringUtils.splitByWholeSeparator(\"ab-!-cd-!-ef\",&nbsp;\"-!-\",&nbsp;2);//---[\"ab\",&nbsp;\"cd-!-ef\"]\r\nStringUtils.splitByWholeSeparatorPreserveAllTokens(\"ab::cd:ef\",&nbsp;\":\");//-[\"ab\",\"&nbsp;\",\"cd\",\"ef\"]\r\n\r\n//去除首尾空格，类似trim……（stripStart、stripEnd、stripAll、stripAccents）\r\nStringUtils.strip(\"&nbsp;ab&nbsp;c&nbsp;\");//---\"ab&nbsp;c\"\r\nStringUtils.stripToNull(null);//---null\r\nStringUtils.stripToEmpty(null);//---\"\"\r\n\r\n//截取字符串\r\nStringUtils.substring(\"abcd\",&nbsp;2);//---\"cd\"\r\nStringUtils.substring(\"abcdef\",&nbsp;2,&nbsp;4);//---\"cd\"\r\n\r\n//left、right从左(右)开始截取n位字符\r\nStringUtils.left(\"abc\",&nbsp;2);//---\"ab\"\r\nStringUtils.right(\"abc\",&nbsp;2);//---\"bc\"\r\n//从第n位开始截取m位字符&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;n&nbsp;&nbsp;m\r\nStringUtils.mid(\"abcdefg\",&nbsp;2,&nbsp;4);//---\"cdef\"\r\n&nbsp;&nbsp;\r\nStringUtils.substringBefore(\"abcba\",&nbsp;\"b\");//---\"a\"\r\nStringUtils.substringBeforeLast(\"abcba\",&nbsp;\"b\");//---\"abc\"\r\nStringUtils.substringAfter(\"abcba\",&nbsp;\"b\");//---\"cba\"\r\nStringUtils.substringAfterLast(\"abcba\",&nbsp;\"b\");//---\"a\"\r\n&nbsp;&nbsp;\r\nStringUtils.substringBetween(\"tagabctag\",&nbsp;\"tag\");//---\"abc\"\r\nStringUtils.substringBetween(\"yabczyabcz\",&nbsp;\"y\",&nbsp;\"z\");//---\"abc\"</pre>\n  <p style=\"white-space: normal;\">&nbsp;</p>\n  <p style=\"white-space: normal;\">&nbsp; &nbsp;</p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\"><strong>三、其它类简介</strong></p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\">RandomStringUtils:</p>\n  <pre courier=\"\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 9.5px; border-radius: 4px; background-color: rgb(245, 245, 245); box-sizing: border-box; overflow: auto; font-size: 13px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; overflow-wrap: break-word; border: 1px solid rgb(204, 204, 204); white-space: pre-wrap;\">//随机生成n位数数字\r\nRandomStringUtils.randomNumeric(n);\r\n//在指定字符串中生成长度为n的随机字符串\r\nRandomStringUtils.random(n,&nbsp;\"abcdefghijk\");\r\n//指定从字符或数字中生成随机字符串\r\nSystem.out.println(RandomStringUtils.random(n,&nbsp;true,&nbsp;false));&nbsp;&nbsp;\r\nSystem.out.println(RandomStringUtils.random(n,&nbsp;false,&nbsp;true));</pre>\n  <p style=\"white-space: normal;\">&nbsp;</p>\n  <p style=\"white-space: normal;\">&nbsp; &nbsp;</p>\n  <p style=\"white-space: normal;\"><span pingfang=\"\" lantinghei=\"\" microsoft=\"\" background-color:=\"\" style=\"color: rgb(51, 51, 51);\">NumberUtils:</span></p>\n  <p style=\"white-space: normal;\"><span pingfang=\"\" lantinghei=\"\" microsoft=\"\" background-color:=\"\" style=\"color: rgb(51, 51, 51);\"></span></p>\n  <pre courier=\"\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 9.5px; border-radius: 4px; background-color: rgb(245, 245, 245); box-sizing: border-box; overflow: auto; font-size: 13px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; overflow-wrap: break-word; border: 1px solid rgb(204, 204, 204); white-space: pre-wrap;\">//从数组中选出最大值\r\nNumberUtils.max(new&nbsp;int[]&nbsp;{&nbsp;1,&nbsp;2,&nbsp;3,&nbsp;4&nbsp;});//---4\r\n//判断字符串是否全是整数\r\nNumberUtils.isDigits(\"153.4\");//--false\r\n//判断字符串是否是有效数字\r\nNumberUtils.isNumber(\"0321.1\");//---false</pre>\n  <p style=\"white-space: normal;\">&nbsp;</p>\n  <p style=\"white-space: normal;\">&nbsp; &nbsp;</p>\n  <p style=\"white-space: normal;\"><span pingfang=\"\" lantinghei=\"\" microsoft=\"\" background-color:=\"\" style=\"color: rgb(51, 51, 51);\">ArrayUtils:</span></p>\n  <p style=\"white-space: normal;\"><span pingfang=\"\" lantinghei=\"\" microsoft=\"\" background-color:=\"\" style=\"color: rgb(51, 51, 51);\"></span></p>\n  <pre courier=\"\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 9.5px; border-radius: 4px; background-color: rgb(245, 245, 245); box-sizing: border-box; overflow: auto; font-size: 13px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; overflow-wrap: break-word; border: 1px solid rgb(204, 204, 204); white-space: pre-wrap;\">//创建数组\r\nString[]&nbsp;array&nbsp;=&nbsp;ArrayUtils.toArray(\"1\",&nbsp;\"2\");\r\n//判断两个数据是否相等，如果内容相同，&nbsp;顺序相同&nbsp;则返回&nbsp;true\r\nArrayUtils.isEquals(arr1,arr2);\r\n//判断数组中是否包含某一对象\r\nArrayUtils.contains(arr,&nbsp;\"33\");\r\n//二维数组转换成MAP\r\nMap&nbsp;map&nbsp;=&nbsp;ArrayUtils.toMap(new&nbsp;String[][]&nbsp;{\r\n&nbsp;&nbsp;&nbsp;&nbsp;{&nbsp;\"RED\",&nbsp;\"#FF0000\"&nbsp;},&nbsp;{&nbsp;\"GREEN\",&nbsp;\"#00FF00\"&nbsp;},&nbsp;{&nbsp;\"BLUE\",&nbsp;\"#0000FF\"&nbsp;}&nbsp;});</pre>\n  <p style=\"white-space: normal;\">&nbsp;</p>\n  <p style=\"white-space: normal;\">&nbsp; &nbsp;</p>\n  <p style=\"white-space: normal;\"><span pingfang=\"\" lantinghei=\"\" microsoft=\"\" background-color:=\"\" style=\"color: rgb(51, 51, 51);\">DateUtils:</span></p>\n  <pre courier=\"\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 9.5px; border-radius: 4px; background-color: rgb(245, 245, 245); box-sizing: border-box; overflow: auto; font-size: 13px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; overflow-wrap: break-word; border: 1px solid rgb(204, 204, 204); white-space: pre-wrap;\">//日期加n天\r\nDateUtils.addDays(new&nbsp;Date(),&nbsp;n);\r\n//判断是否同一天\r\nDateUtils.isSameDay(date1,&nbsp;date2);\r\n//字符串时间转换为Date\r\nDateUtils.parseDate(str,&nbsp;parsePatterns);</pre>\n  <p style=\"white-space: normal;\">&nbsp;</p>\n  <p style=\"white-space: normal;\">&nbsp;</p>\n  <p style=\"white-space: normal;\"><br></p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\"><strong>四、结语</strong></p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\">&nbsp;&nbsp;&nbsp;&nbsp;本文只是简单的介绍了commons-lang中的一些常用工具类，还有许多挺实用的就不一一列举。还是要自己去查阅文档试用了才能体会到它的简便。</p>\n  <p pingfang=\"\" lantinghei=\"\" microsoft=\"\" white-space:=\"\" background-color:=\"\" style=\"margin-top: 26px; margin-bottom: 26px; white-space: normal; padding: 0px; color: rgb(51, 51, 51);\"><br></p>\n  <p style=\"white-space: normal;\">原文：<a href=\"https://zhidao.baidu.com/question/587200456967870805.html\">https://zhidao.baidu.com/question/587200456967870805.html</a></p>\n  <p><br></p>\n </body>\n</html>');

-- ----------------------------
-- Table structure for t_attachs
-- ----------------------------
DROP TABLE IF EXISTS `t_attachs`;
CREATE TABLE `t_attachs` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '名称',
  `content` longtext NOT NULL COMMENT '内容',
  `link` varchar(255) NOT NULL DEFAULT '' COMMENT '链接',
  `is_footer_top` int(2) NOT NULL DEFAULT '-10' COMMENT '是否底部顶',
  `sort` int(8) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` int(2) NOT NULL DEFAULT '10',
  `create_time` datetime NOT NULL,
  `create_by` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attachs
-- ----------------------------
INSERT INTO `t_attachs` VALUES ('1', '关于我', '<h1 class=\"post-title\">关于我</h1>\r\n<div class=\"post-frame\">\r\n	<div class=\"post-content\">\r\n		<p><strong>关于本站</strong><br/></p>\r\n		<p>一个以轻内容为主的博客分享平台。</p>\r\n		<br/>\r\n		<p><strong>关于站长</strong><br/></p>\r\n		<p>90后java程序员一枚，17年入行，喜欢研究java相关技术，一边工作一边积累经验。</p>\r\n		<p>由于本人前端技术实在不咋地，但又想自己搭建一个博客网站，所以此博客系统前端页面参考mtons社区。</p>\r\n		<br/>\r\n		<p><strong>本站架构</strong><br/></p>\r\n		<p>采用通用的Spring Boot + Mybatis框架构建，Shiro作权限控制。</p>\r\n		<p>页面端采用FreeMarker模板引擎。</p>\r\n		<p>存储采用Redis、MySQL。</p>\r\n		<p>搜索引擎使用ES。</p>\r\n		\r\n	</div>\r\n	<div class=\"post-footer\">\r\n	</div>\r\n</div>\r\n	\r\n	', '', '10', '2', '10', '2019-06-06 21:39:29', '1', null, null);
INSERT INTO `t_attachs` VALUES ('2', '常见问题', '<h1 class=\"post-title\">文章规范</h1>\r\n	<div class=\"clearfix post-other\">\r\n		<span class=\"pull-left author\">\r\n			本站注册的用户，均可以发布文章，发文需要满足以下规范：\r\n		</span>\r\n	</div>\r\n	<div class=\"post-frame\">\r\n		<div class=\"post-content\">\r\n			<ol class=\" list-paddingleft-2\">\r\n				<li>\r\n					<p><strong>1.写文章时该注意的事项，什么样的文章才是受欢迎的</strong>\r\n					</p>\r\n					<p>受欢迎的文章应该是</p>\r\n					<p>具有原创性、对他人有启发性且行文排版优雅的文章</p>\r\n					<p>与开发相关的技术分享、开发技巧、工具介绍、技术设想、业界评论、职业心得等</p>\r\n					<p>认真或有趣的交流与分享</p>\r\n					<p>具有原创和启发性</p>\r\n					<p>原创的文章是自己学习和探索的结果，独立的思考会给他人更大的启发，会引导他人去发现、实现可能更加有趣的事。所以，如果是翻译或转载的文章，可以在发布文章时选择[转载]选项，并在文章显要位置注明原作出处。</p>\r\n				</li>\r\n				<li>\r\n					<p>\r\n						<strong>2.哪些文章是不受欢迎的</strong>\r\n					</p>\r\n					<p>很水的贴子式文章</p>\r\n					<p>做个人备忘 / 记录 / 草稿 类的内容更应该是草稿而非正式发表的文章</p>\r\n					<p>杂乱无序的内容</p>\r\n					<p>没有发挥 富文本 的排版优势，文章结构不清晰且内容杂乱</p>\r\n					<p>任何推广（招聘、广告、SEO 等）方面的内容</p>\r\n					<p>推广（招聘、广告、SEO 等）方面的内容实际要比毫无含量的内容更糟糕，是直接对他人的打扰，所以任何推广方面的内容一经出现，将被无条件删除</p>\r\n				</li>\r\n				<li>\r\n					<p>\r\n						<strong>3.什么情况下文章会被隐藏 / 删除</strong>\r\n					</p>\r\n					<p>水贴</p>\r\n					<p>推广（招聘、广告、SEO 等）方面的内容多次出现</p>\r\n					<p>带有人身攻击、辱骂、仇恨等违反条款的内容多次出现</p>\r\n				</li>\r\n				<li>\r\n					<p>\r\n						<strong>4.评论的作用是什么，什么时候使用评论</strong>\r\n					</p>\r\n					<p>当你需要对提问者的问题，或者回答者的答案做出更多的询问和点评时，可以使用评论功能</p>\r\n				</li>\r\n			</ol>\r\n		</div>\r\n		<div class=\"post-footer\">\r\n		</div>\r\n	</div>', '', '10', '1', '10', '2019-06-06 21:59:57', '1', null, null);
INSERT INTO `t_attachs` VALUES ('3', '免责申明', '<h1 class=\"post-title\"><i class=\"fa fa-star\"></i> 免责申明</h1>\r\n<div class=\"post-frame\">\r\n	<div class=\"post-content\">\r\n		<p>本站所有资源都是由网友分享，仅供学习和交流使用，如果侵权了您的合法权益，请发邮件至957164618@qq.com 我们立即删除。</p>\r\n	</div>\r\n	<div class=\"post-footer\">\r\n	</div>\r\n</div>\r\n	\r\n	', '', '-10', '3', '10', '2019-06-11 08:12:10', '1', null, null);

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_id` int(11) NOT NULL COMMENT '评论的用户id',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `create_time` datetime NOT NULL,
  `status` int(2) NOT NULL DEFAULT '10' COMMENT '状态（-1， 10：删除，正常）',
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '上级id（没有为0）',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_article_id` (`article_id`) USING BTREE,
  KEY `idx_unite` (`user_id`,`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comments
-- ----------------------------
INSERT INTO `t_comments` VALUES ('1', '1', 'ddd', '2019-04-28 14:52:26', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('2', '1', '132[饥饿]', '2019-04-28 10:14:03', '10', '6', '0');
INSERT INTO `t_comments` VALUES ('3', '4', '撒旦撒', '2019-04-28 11:05:56', '10', '6', '0');
INSERT INTO `t_comments` VALUES ('4', '1', '大撒大撒两节课', '2019-05-01 08:48:38', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('5', '1', '法撒旦', '2019-05-01 08:50:13', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('6', '1', '自评了', '2019-05-01 08:51:22', '10', '5', '5');
INSERT INTO `t_comments` VALUES ('7', '1', '消息小骚', '2019-05-01 08:51:34', '10', '5', '5');
INSERT INTO `t_comments` VALUES ('8', '1', '的撒大', '2019-05-01 08:56:42', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('9', '1', '的撒大', '2019-05-01 08:56:44', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('10', '1', '发送发送', '2019-05-01 08:56:46', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('11', '1', '的撒大', '2019-05-01 09:24:25', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('12', '1', '[右哼哼]', '2019-05-01 09:25:00', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('13', '1', '4654', '2019-05-01 09:25:48', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('14', '1', '的撒', '2019-05-01 09:25:59', '10', '5', '1');
INSERT INTO `t_comments` VALUES ('15', '1', '[害羞]', '2019-05-01 10:46:49', '10', '5', '5');
INSERT INTO `t_comments` VALUES ('16', '1', '45646', '2019-05-01 10:47:20', '10', '5', '5');
INSERT INTO `t_comments` VALUES ('17', '1', '555', '2019-05-01 11:13:19', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('18', '1', '[得意]', '2019-05-02 09:35:38', '10', '5', '12');
INSERT INTO `t_comments` VALUES ('19', '1', 'dddd', '2019-05-02 09:35:57', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('20', '1', 'ddddd', '2019-05-02 09:36:08', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('21', '1', '[大兵]', '2019-05-02 09:36:25', '10', '5', '20');
INSERT INTO `t_comments` VALUES ('22', '1', '[饥饿]', '2019-05-02 09:36:31', '10', '5', '20');
INSERT INTO `t_comments` VALUES ('23', '1', '[害羞][嘘…]', '2019-05-02 09:48:54', '10', '5', '5');
INSERT INTO `t_comments` VALUES ('24', '1', '564[得意]', '2019-05-02 09:53:27', '10', '5', '20');
INSERT INTO `t_comments` VALUES ('25', '1', '66', '2019-05-02 09:53:58', '10', '5', '20');
INSERT INTO `t_comments` VALUES ('26', '1', '666', '2019-05-02 09:54:36', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('27', '1', '1', '2019-05-02 10:01:40', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('28', '1', '11111', '2019-05-02 10:03:40', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('29', '1', '111111', '2019-05-02 10:03:43', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('30', '1', '我的世界十一', '2019-05-02 10:03:56', '10', '5', '0');
INSERT INTO `t_comments` VALUES ('31', '1', '4546', '2019-05-02 10:09:05', '10', '3', '0');
INSERT INTO `t_comments` VALUES ('32', '1', '[疑问]', '2019-05-02 10:09:10', '10', '3', '31');
INSERT INTO `t_comments` VALUES ('33', '1', '大撒大撒', '2019-05-02 10:09:13', '10', '3', '0');
INSERT INTO `t_comments` VALUES ('34', '1', '[流泪]', '2019-05-02 10:09:25', '10', '3', '33');
INSERT INTO `t_comments` VALUES ('35', '1', '[发呆]', '2019-05-02 10:09:30', '10', '3', '0');
INSERT INTO `t_comments` VALUES ('36', '1', '[色]', '2019-05-02 10:09:34', '10', '3', '0');
INSERT INTO `t_comments` VALUES ('37', '1', '大撒大撒', '2019-05-02 10:09:44', '10', '3', '36');
INSERT INTO `t_comments` VALUES ('38', '1', '的撒大', '2019-05-02 10:09:48', '10', '3', '36');
INSERT INTO `t_comments` VALUES ('39', '1', '大撒大撒', '2019-05-02 10:09:53', '10', '3', '36');
INSERT INTO `t_comments` VALUES ('40', '1', '打上的', '2019-05-02 10:09:58', '10', '3', '36');
INSERT INTO `t_comments` VALUES ('41', '1', '好[鼓掌]', '2019-05-03 08:57:54', '10', '9', '0');
INSERT INTO `t_comments` VALUES ('42', '1', '666', '2019-05-03 08:57:58', '10', '9', '0');
INSERT INTO `t_comments` VALUES ('43', '1', '的撒', '2019-05-04 11:09:39', '10', '9', '42');
INSERT INTO `t_comments` VALUES ('44', '1', '的撒', '2019-05-04 11:09:43', '10', '9', '41');
INSERT INTO `t_comments` VALUES ('45', '1', '的撒', '2019-05-04 11:09:55', '10', '9', '0');
INSERT INTO `t_comments` VALUES ('46', '1', 'dsad ', '2019-05-04 11:11:17', '10', '9', '0');
INSERT INTO `t_comments` VALUES ('47', '1', 'wode shkj ', '2019-05-04 11:11:30', '10', '9', '46');
INSERT INTO `t_comments` VALUES ('48', '1', '我的世界', '2019-05-04 11:11:37', '10', '9', '46');
INSERT INTO `t_comments` VALUES ('49', '1', '一起来玩呀', '2019-05-04 11:11:45', '10', '9', '46');
INSERT INTO `t_comments` VALUES ('50', '1', '我好想你', '2019-05-04 11:11:51', '10', '9', '46');
INSERT INTO `t_comments` VALUES ('51', '1', 'dsadsa', '2019-05-12 08:33:26', '10', '17', '0');

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统配置id',
  `key` varchar(255) NOT NULL DEFAULT '' COMMENT '配置key',
  `type` int(8) DEFAULT '0' COMMENT '配置type',
  `value` varchar(255) NOT NULL DEFAULT '' COMMENT '配置value',
  `status` int(2) DEFAULT '10' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_config
-- ----------------------------
INSERT INTO `t_config` VALUES ('1', 'site_name', '0', 'IT云博客', '10');
INSERT INTO `t_config` VALUES ('2', 'site_title', '0', 'IT云博客, 程序员的技术博客网站', '10');
INSERT INTO `t_config` VALUES ('3', 'site_domain', '0', 'http://localhost:8888', '10');
INSERT INTO `t_config` VALUES ('4', 'site_keywords', '0', 'IT云博客,互联网,网页开发,社区,资料,学习,Java,Spring Boot,Spring Cloud,微服务,数据库,JavaScript,Web前端,网络技术,Python,微信开发,大数据,编程教程', '10');
INSERT INTO `t_config` VALUES ('5', 'site_description', '0', 'IT云博客（www.itcloud.top）分享你的技术,致力于提供一个IT技术博客分享平台，包含了各互联网技术资料，主流编程方向Java,Spring Boot,Spring Cloud,微服务,数据库,JavaScript,Web前端,网络技术,Python,微信开发,大数据等等！', '10');
INSERT INTO `t_config` VALUES ('6', 'site_editor', '1', 'ueditor', '10');
INSERT INTO `t_config` VALUES ('7', 'site_metas', '0', '', '10');
INSERT INTO `t_config` VALUES ('8', 'site_copyright', '0', '', '10');
INSERT INTO `t_config` VALUES ('9', 'site_icp', '0', '', '10');
INSERT INTO `t_config` VALUES ('10', 'site_advs_right', '0', '', '10');
INSERT INTO `t_config` VALUES ('11', 'email_template_title', '0', 'IT云博客邮箱验证', '10');

-- ----------------------------
-- Table structure for t_favors
-- ----------------------------
DROP TABLE IF EXISTS `t_favors`;
CREATE TABLE `t_favors` (
  `create_time` datetime NOT NULL,
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `own_id` int(11) NOT NULL COMMENT '喜欢者id',
  PRIMARY KEY (`article_id`,`own_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_favors
-- ----------------------------
INSERT INTO `t_favors` VALUES ('2019-05-01 09:29:01', '5', '1');
INSERT INTO `t_favors` VALUES ('2019-04-28 16:24:25', '6', '4');
INSERT INTO `t_favors` VALUES ('2019-05-03 08:58:02', '9', '1');
INSERT INTO `t_favors` VALUES ('2019-05-09 11:39:14', '14', '1');
INSERT INTO `t_favors` VALUES ('2019-05-09 14:12:26', '16', '1');
INSERT INTO `t_favors` VALUES ('2019-05-25 19:53:57', '21', '1');
INSERT INTO `t_favors` VALUES ('2019-05-30 20:53:54', '22', '1');

-- ----------------------------
-- Table structure for t_follows
-- ----------------------------
DROP TABLE IF EXISTS `t_follows`;
CREATE TABLE `t_follows` (
  `create_time` datetime NOT NULL,
  `follow_id` int(11) NOT NULL COMMENT '粉丝id',
  `user_id` int(11) NOT NULL COMMENT '被关注者id',
  PRIMARY KEY (`follow_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_follows
-- ----------------------------
INSERT INTO `t_follows` VALUES ('2019-05-30 09:28:10', '1', '11');
INSERT INTO `t_follows` VALUES ('2019-05-31 13:23:59', '1', '12');
INSERT INTO `t_follows` VALUES ('2019-04-28 16:24:19', '4', '1');
INSERT INTO `t_follows` VALUES ('2019-05-30 09:33:29', '11', '1');

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类标签id',
  `group_key` varchar(255) NOT NULL COMMENT '分类key',
  `group_value` varchar(255) NOT NULL COMMENT '分类value',
  `status` int(2) NOT NULL DEFAULT '10' COMMENT '状态（-1，失效，10：正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES ('1', 'tutorial', '教程', '10');
INSERT INTO `t_group` VALUES ('2', 'software', '软件', '10');

-- ----------------------------
-- Table structure for t_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_logs`;
CREATE TABLE `t_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志记录id',
  `create_time` datetime NOT NULL,
  `ip` varchar(16) NOT NULL COMMENT '登录ip',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_logs
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL COMMENT '名称',
  `permission` varchar(64) NOT NULL DEFAULT '' COMMENT '权限',
  `sort` int(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `url` varchar(32) DEFAULT NULL COMMENT '请求路径',
  `level` int(2) NOT NULL COMMENT '级别',
  `parent_id` int(11) NOT NULL COMMENT '父级id',
  `parent_ids` varchar(255) NOT NULL DEFAULT '',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` int(11) NOT NULL,
  `update_by` int(11) DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '10',
  PRIMARY KEY (`id`),
  UNIQUE KEY `que_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '根目录', '', '1', '/', '0', '0', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('2', '后台管理', 'sys:admin', '1', 'admin', '1', '1', ' ', '', '2019-05-16 10:23:28', '2019-05-26 12:43:36', '1', null, '10');
INSERT INTO `t_menu` VALUES ('4', '文章管理', 'sys:article:view', '2', 'admin/article/list', '2', '2', ' ', 'fa fa-clone icon-xlarge', '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('5', '文章修改', 'sys:article:edit', '1', null, '3', '4', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('6', '文章查看', 'sys:article:info', '1', '', '3', '4', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('8', '评论管理', 'sys:comments:view', '4', 'admin/comments/list', '2', '2', ' ', 'fa fa-comments-o icon-xlarge', '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('9', '删除评论', 'sys:comments:delete', '1', null, '3', '8', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('10', '评论查看', 'sys:comments:info', '1', '', '3', '8', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('11', '栏目管理', 'sys:group:view', '5', 'admin/group/list', '2', '2', ' ', 'fa fa-tags icon-xlarge', '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('12', '删除栏目', 'sys:group:delete', '1', null, '3', '11', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('13', '修改栏目', 'sys:group:edit', '2', null, '3', '11', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('15', '系统配置', 'sys:config:view', '6', 'admin/config/view', '2', '2', ' ', 'fa fa-sun-o icon-xlarge', '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('16', '修改配置', 'sys:config:edit', '1', null, '3', '15', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('17', '用户管理', 'sys:users:view', '3', 'admin/user/list', '2', '2', ' ', 'fa fa-user icon-xlarge', '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('18', '禁用用户', 'sys:users:edit', '1', '', '3', '17', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('19', '修改密码', 'sys:users:edit', '1', null, '3', '17', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('20', '用户查看', 'sys:users:info', '1', '', '3', '17', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('35', '角色管理', 'sys:roles:view', '7', 'admin/role/list', '2', '2', ' ', 'fa fa fa-registered icon-xlarge', '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('36', '角色修改', 'sys:roles:edit', '0', null, '3', '35', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('37', '角色查看', 'sys:roles:info', '1', '', '3', '35', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('41', '菜单管理', 'sys:menus:view', '8', 'admin/menu/list', '2', '2', ' ', 'fa fa-reorder icon-xlarge', '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('42', '菜单修改', 'sys:menus:edit', '0', '', '3', '41', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('43', '菜单查看', 'sys:menus:info', '1', '', '3', '41', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('45', '友情链接', 'sys:link:view', '8', 'admin/link/list', '2', '2', ' ', 'fa fa-link', '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('46', '友情链接修改', 'sys:link:edit', '0', '', '3', '45', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('47', '友情链接查看', 'sys:link:info', '0', '', '3', '45', ' ', null, '2019-05-16 10:23:28', null, '1', null, '10');
INSERT INTO `t_menu` VALUES ('48', '标签管理', 'sys:tags:view', '7', 'admin/tags/list', '2', '2', '', 'fa fa-tags icon-xlarge', '2019-05-26 13:04:07', '2019-05-26 13:05:42', '1', '1', '10');
INSERT INTO `t_menu` VALUES ('49', '标签管理修改', 'sys:tags:edit', '1', '', '3', '48', '', null, '2019-05-26 13:06:37', null, '1', null, '10');

-- ----------------------------
-- Table structure for t_notify
-- ----------------------------
DROP TABLE IF EXISTS `t_notify`;
CREATE TABLE `t_notify` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `create_time` datetime NOT NULL,
  `event` int(2) NOT NULL COMMENT '事件',
  `from_id` int(11) DEFAULT NULL COMMENT '触发者id',
  `to_id` int(11) NOT NULL COMMENT '接收者id',
  `article_id` int(11) DEFAULT NULL COMMENT '文章id',
  `status` int(2) NOT NULL DEFAULT '5' COMMENT '状态（5：未读，10：已读）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notify
-- ----------------------------
INSERT INTO `t_notify` VALUES ('1', '2019-04-25 16:42:24', '2', '1', '4', null, '10');
INSERT INTO `t_notify` VALUES ('2', '2019-04-25 18:10:03', '2', '4', '1', null, '10');
INSERT INTO `t_notify` VALUES ('6', '2019-04-27 11:21:11', '1', '1', '1', '6', '10');
INSERT INTO `t_notify` VALUES ('7', '2019-04-28 11:06:03', '3', '4', '1', '6', '10');
INSERT INTO `t_notify` VALUES ('8', '2019-04-28 16:21:30', '2', '1', '4', null, '10');
INSERT INTO `t_notify` VALUES ('9', '2019-04-28 16:24:20', '2', '4', '1', null, '10');
INSERT INTO `t_notify` VALUES ('10', '2019-04-28 16:24:25', '1', '4', '1', '6', '10');
INSERT INTO `t_notify` VALUES ('11', '2019-04-28 16:26:11', '2', '1', '4', null, '10');
INSERT INTO `t_notify` VALUES ('12', '2019-04-28 16:31:24', '2', '1', '4', null, '10');
INSERT INTO `t_notify` VALUES ('13', '2019-05-01 08:48:38', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('14', '2019-05-01 08:50:13', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('15', '2019-05-01 08:51:22', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('16', '2019-05-01 08:51:34', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('17', '2019-05-01 08:56:42', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('18', '2019-05-01 08:56:44', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('19', '2019-05-01 08:56:46', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('20', '2019-05-01 09:24:25', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('21', '2019-05-01 09:25:00', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('22', '2019-05-01 09:25:48', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('23', '2019-05-01 09:25:59', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('24', '2019-05-01 09:29:01', '1', '1', '1', null, '10');
INSERT INTO `t_notify` VALUES ('25', '2019-05-01 11:13:19', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('26', '2019-05-02 09:35:57', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('27', '2019-05-02 09:36:08', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('28', '2019-05-02 09:54:41', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('29', '2019-05-02 10:01:40', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('30', '2019-05-02 10:03:40', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('31', '2019-05-02 10:03:43', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('32', '2019-05-02 10:03:56', '3', '1', '1', '5', '10');
INSERT INTO `t_notify` VALUES ('33', '2019-05-02 10:09:05', '3', '1', '1', '3', '10');
INSERT INTO `t_notify` VALUES ('34', '2019-05-02 10:09:13', '3', '1', '1', '3', '10');
INSERT INTO `t_notify` VALUES ('35', '2019-05-02 10:09:30', '3', '1', '1', '3', '10');
INSERT INTO `t_notify` VALUES ('36', '2019-05-02 10:09:34', '3', '1', '1', '3', '10');
INSERT INTO `t_notify` VALUES ('37', '2019-05-03 08:57:54', '3', '1', '1', '9', '10');
INSERT INTO `t_notify` VALUES ('38', '2019-05-03 08:57:58', '3', '1', '1', '9', '10');
INSERT INTO `t_notify` VALUES ('39', '2019-05-03 08:58:02', '1', '1', '1', null, '10');
INSERT INTO `t_notify` VALUES ('40', '2019-05-04 11:09:56', '3', '1', '1', '9', '10');
INSERT INTO `t_notify` VALUES ('41', '2019-05-04 11:11:17', '3', '1', '1', '9', '10');
INSERT INTO `t_notify` VALUES ('42', '2019-05-09 11:39:14', '1', '1', '1', null, '10');
INSERT INTO `t_notify` VALUES ('43', '2019-05-09 14:12:26', '1', '1', '1', null, '10');
INSERT INTO `t_notify` VALUES ('44', '2019-05-12 08:33:26', '3', '1', '1', '17', '10');
INSERT INTO `t_notify` VALUES ('45', '2019-05-12 20:31:40', '5', '-1', '8', null, '10');
INSERT INTO `t_notify` VALUES ('46', '2019-05-12 20:39:20', '5', '-1', '9', null, '10');
INSERT INTO `t_notify` VALUES ('47', '2019-05-13 09:16:10', '5', '-1', '10', null, '10');
INSERT INTO `t_notify` VALUES ('50', '2019-05-25 19:53:57', '1', '1', '1', '21', '10');
INSERT INTO `t_notify` VALUES ('51', '2019-05-29 14:38:01', '5', '-1', '12', null, '10');
INSERT INTO `t_notify` VALUES ('52', '2019-05-30 09:28:10', '2', '1', '11', null, '10');
INSERT INTO `t_notify` VALUES ('53', '2019-05-30 09:33:29', '2', '11', '1', null, '10');
INSERT INTO `t_notify` VALUES ('54', '2019-05-30 20:53:54', '1', '1', '1', '22', '10');
INSERT INTO `t_notify` VALUES ('55', '2019-05-31 13:24:00', '2', '1', '12', null, '5');
INSERT INTO `t_notify` VALUES ('56', '2019-06-02 12:23:11', '6', '-1', '1', null, '5');
INSERT INTO `t_notify` VALUES ('57', '2019-06-03 12:24:44', '5', '-1', '13', null, '10');
INSERT INTO `t_notify` VALUES ('58', '2019-06-04 21:05:50', '6', '-1', '1', null, '5');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL COMMENT '权限id',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `url` varchar(32) NOT NULL COMMENT '路径',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `status` int(2) NOT NULL COMMENT '状态（-10，10：失效，启用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'sys:home', '/admin', '后台首页', '10');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(8) NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `que_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '系统管理员');
INSERT INTO `t_role` VALUES ('2', 'user', '普通用户');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '1');
INSERT INTO `t_role_menu` VALUES ('1', '2');
INSERT INTO `t_role_menu` VALUES ('1', '4');
INSERT INTO `t_role_menu` VALUES ('1', '5');
INSERT INTO `t_role_menu` VALUES ('1', '6');
INSERT INTO `t_role_menu` VALUES ('1', '8');
INSERT INTO `t_role_menu` VALUES ('1', '9');
INSERT INTO `t_role_menu` VALUES ('1', '10');
INSERT INTO `t_role_menu` VALUES ('1', '11');
INSERT INTO `t_role_menu` VALUES ('1', '12');
INSERT INTO `t_role_menu` VALUES ('1', '13');
INSERT INTO `t_role_menu` VALUES ('1', '15');
INSERT INTO `t_role_menu` VALUES ('1', '16');
INSERT INTO `t_role_menu` VALUES ('1', '17');
INSERT INTO `t_role_menu` VALUES ('1', '18');
INSERT INTO `t_role_menu` VALUES ('1', '19');
INSERT INTO `t_role_menu` VALUES ('1', '20');
INSERT INTO `t_role_menu` VALUES ('1', '35');
INSERT INTO `t_role_menu` VALUES ('1', '36');
INSERT INTO `t_role_menu` VALUES ('1', '37');
INSERT INTO `t_role_menu` VALUES ('1', '41');
INSERT INTO `t_role_menu` VALUES ('1', '42');
INSERT INTO `t_role_menu` VALUES ('1', '43');
INSERT INTO `t_role_menu` VALUES ('1', '45');
INSERT INTO `t_role_menu` VALUES ('1', '46');
INSERT INTO `t_role_menu` VALUES ('1', '47');
INSERT INTO `t_role_menu` VALUES ('1', '48');
INSERT INTO `t_role_menu` VALUES ('1', '49');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1');

-- ----------------------------
-- Table structure for t_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_tags`;
CREATE TABLE `t_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `status` int(2) NOT NULL COMMENT '状态（-10，10：隐藏，显示）',
  `sort` int(2) NOT NULL COMMENT '顺序',
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `que_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tags
-- ----------------------------
INSERT INTO `t_tags` VALUES ('1', 'Java', '10', '1', null, null, null, null);
INSERT INTO `t_tags` VALUES ('2', 'Spring Cloud', '10', '3', null, null, null, null);
INSERT INTO `t_tags` VALUES ('3', 'Dubbo', '10', '2', null, null, null, null);
INSERT INTO `t_tags` VALUES ('4', '数据库', '10', '5', null, null, null, null);
INSERT INTO `t_tags` VALUES ('5', 'Linux', '10', '6', null, null, null, null);
INSERT INTO `t_tags` VALUES ('6', 'Spring Boot', '10', '4', null, null, null, null);
INSERT INTO `t_tags` VALUES ('7', 'Javascript', '10', '7', null, null, null, null);
INSERT INTO `t_tags` VALUES ('8', 'Web前端', '10', '10', null, null, null, null);
INSERT INTO `t_tags` VALUES ('10', 'Jquery', '10', '8', null, null, null, null);
INSERT INTO `t_tags` VALUES ('11', 'Vue.js', '10', '9', null, null, null, null);
INSERT INTO `t_tags` VALUES ('12', '版本控制', '10', '12', null, null, null, null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `email` varchar(20) NOT NULL DEFAULT '' COMMENT '邮箱',
  `last_login` datetime DEFAULT NULL COMMENT '用户最后一次登录时间',
  `mobile` varchar(16) DEFAULT '' COMMENT '手机号码',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `status` int(2) NOT NULL DEFAULT '10' COMMENT '状态（-1：冻结，10：正常）',
  `user_name` varchar(16) NOT NULL,
  `nick_name` varchar(16) NOT NULL DEFAULT '',
  `avatar` varchar(255) NOT NULL COMMENT '用户头像',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `sex` int(1) NOT NULL DEFAULT '1' COMMENT '性别（1，0：男：女）',
  `source` int(8) NOT NULL DEFAULT '0' COMMENT '分数',
  `active_email` int(1) NOT NULL DEFAULT '0',
  `comments` int(8) NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `fans` int(8) NOT NULL DEFAULT '0' COMMENT '粉丝数',
  `favors` int(8) NOT NULL DEFAULT '0' COMMENT '我收藏的文章数',
  `follows` int(8) NOT NULL DEFAULT '0' COMMENT '我关注的博主数量',
  `posts` int(8) NOT NULL DEFAULT '0' COMMENT '发文数量',
  `signature` varchar(128) DEFAULT '' COMMENT '签名',
  `salt` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `que_nick_name` (`nick_name`),
  UNIQUE KEY `que_email` (`email`),
  UNIQUE KEY `que_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '957164618@qq.com', '2017-09-29 17:25:22', null, '5f3ef05f751202d3c02a67857b1f07cb', '10', 'admin', '233332', '/accessory/admin/avatar_20190509113949148.jpg', '2019-03-12 17:32:23', '2019-05-13 16:01:04', '1', '0', '0', '3', '4', '0', '0', '13', '我的世界美欧下雪？？？？', 'uTHJCxi');
INSERT INTO `t_user` VALUES ('11', '195164618@qq.com', null, '', 'e75e0cb7b72dad06b872f7017086416a', '10', 'ht18522', 'admin2', '/accessory/ht18522/avatar_20190513160157123.jpg', '2019-05-13 14:00:40', '2019-05-13 16:02:03', '1', '0', '0', '0', '1', '0', '0', '1', '23123123', 'UeTzfY1');
INSERT INTO `t_user` VALUES ('12', '2323810996@qq.com', null, '', 'a9dd9034c24b69c2a8543cfc30265976', '10', 'admin2', '小甜心', '/static/assets/images/ava/default.png', '2019-05-29 14:38:01', null, '1', '0', '0', '0', '1', '0', '0', '0', '', 'mvz9fpW');
INSERT INTO `t_user` VALUES ('13', '2323810991@qq.com', null, '', '6f534e2dc3a71deb433e4deda7e6682f', '10', 'ht19523', '百特', '/static/assets/images/ava/default.png', '2019-06-03 12:24:44', null, '1', '0', '0', '0', '0', '0', '0', '0', '', 'daPcrP7');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');
