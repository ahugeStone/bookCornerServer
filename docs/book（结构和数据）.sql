/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 25/07/2018 16:43:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_baseinfo
-- ----------------------------
DROP TABLE IF EXISTS `book_baseinfo`;
CREATE TABLE `book_baseinfo`  (
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `bookName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书名',
  `bookWriter` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书作者',
  `bookBrief` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图书简介,默认为‘-’',
  `bookType` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书分类：0：党建；1：技术',
  `bookStatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书状态：0：借出；1：在库',
  `bookSource` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '图书来源：0：采购；1：捐赠',
  `bookBuyer` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图书购买者',
  `bookTime` datetime(0) NOT NULL COMMENT '图书购买/捐赠时间',
  `bookRemark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图书备注,默认为‘-’',
  `bookLikeNum` int(11) NOT NULL DEFAULT 0 COMMENT '图书点赞数：默认为0',
  `bookCommentNum` int(11) NOT NULL DEFAULT 0 COMMENT '图书评论数:默认为0',
  `recTime` datetime(0) NOT NULL COMMENT '入库时间',
  `bookScore` int(11) NOT NULL COMMENT '得分',
  PRIMARY KEY (`bookId`) USING BTREE,
  INDEX `idx_bookName`(`bookName`) USING BTREE,
  INDEX `idx_bookType`(`bookType`) USING BTREE,
  INDEX `idx_bookStatus`(`bookStatus`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_baseinfo
-- ----------------------------
INSERT INTO `book_baseinfo` VALUES (1, '中国震撼', '张维为', '邓小平资深翻译，日内瓦外交与国际关系学院教授，“中国模式”最坚定的支持者和理论研究者，亲历百国现实，从全球视野中比较“中国经验”，对“中国模式”做出最强有力的理论总结。“中国崛起”震撼了全世界，“中国模式”也成为了国际学界、理论界热议的话题。张维为教授通过自己走访一百多个国家的所见所闻，以国际关系学者的深厚学术背景，以其独特观察和理性分析，丰富了“中国模式”的深刻涵义，更提出了中国作为一个“文明型国家”崛起的命题。中国的崛起不是一个普通国家的崛起，而是一个五千年连绵不断的伟大文明的复兴，是一个“文明型国家”的崛起。“文明型国家”崛起的深度、广度和力度都是人类历史上前所未见的。这种“文明型国家”有能力汲取其他文明的一切长处而不失去自我，并对世界文明作出原创性的贡献。本书是“中国模式”论的最坚实有力的理论著作，其归纳的“中国模式”的八大特点和八大理念，是“中国模式”论精辟的理论总结，它将丰富关于“中国模式”的理论研究，同时，也让国人以及世人更客观地认识中国崛起的事实，理解中国崛起背后的文化内涵。', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 1, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (2, '中国触动', '张维为', '30年来，张维为教授走访了100多个国家和地区。自1980年代第一次出国感受到的震惊，到今天在海外随处可见的“中国热”；从作为国家领导人的翻译参与国事访问，到成为有一定影响力的学术研究者参加国际交流，作者力求从一个比较宽广的国际视野出发，来观察世界、思考中国，特别是探讨与中国崛起有关的热点问题，再尖锐的问题，也不回避。中国崛起触动了世界。中国人对于自己的发展，对于国际问题的认知，应该在汲取世界智慧的同时，也用自己的价值观加以检验，用自己的话语加以论述，客观自信地评述自己的国家和外部的世界。', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 1, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (3, '中国超越', '张维为', '本书是作者走访百国后的第三本思考型著作，在《中国触动》和《中国震撼》的基础上，作者探讨了中国对以美国为代表的西方和西方模式的超越，包括在经济总量、百姓资产、社会保障、科技创新、制度安排等领域的超越。在这些领域中，中国在不少方面已经超越了美国，在许多方面不久将超越美国，在另外一些方面，通过继续不断的努力，最终也可能超越，而其中最有意义的，无疑是中国的制度安排，也就是政治制度方面的超越。此外，作者还从“文明型国家”的视角，探讨了中国话语对西方话语的超越，解释了中国道路、中国模式和中国制度安排及其背后的理念，论证了中国许多做法的深层次的合理性。中国是世界上唯一维系了五千年文明而没有中断的伟大国家，历史上长期领先于世界，落后于西方是近代发生的事。而自现代以来，中国迅速“赶超”西方的背后是中国道路和中国模式的总体成功，这种成功源于对中华文明底蕴的把握、对中国红色传统的承袭，以及对国际有益经验的借鉴。中国正带着一个“文明型国家”的光荣与梦想，实现中国人百年奋斗的目标，重返世界之巅。', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (4, '习近平讲故事', '人民日报评论部', '图书简介', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (5, '浴血荣光', '金一南', '从世界政党史上很难找到，有哪一个政党像中国共产党这样，领导层像割韭菜一样，一批一批被对方屠杀。这就是中国革命和其他革命都无法类比的空前残酷性。那是一个热血澎湃、狂飙突进的时代。中国共产党的一批年轻人浴血奋斗，国民党的一批年轻人也在拼命奋斗，共产国际的一批年轻人也在奋斗。这是一个年纪轻轻就干大事的时代，也是一个年纪轻轻就丢掉性命的时代。列宁去世的时候不到54岁。斯大林42岁当上总书记。蒋介石39岁出任国民革命军总司令。李大钊就义时还不到38岁。毛泽东34岁上井冈山。周恩来29岁主持南昌暴动。朱德31岁参加护法战争。博古24岁出任中共中央临时总负责人。聂耳不到23岁谱写《义勇军进行曲》。寻淮洲21岁担任红军军团长。邹容18岁写《革命军》…没有一个人老态龙钟，没有一个人德高望重，而且没有一个人研究长寿，切磋保养，都是主义、奋斗、牺牲、救亡。这样的现象应那个时代而生，也应那个时代而完成', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (6, '习近平用典', '人民日报评论部', '本书的古典名句是中华文化长河中历经砥砺的智慧结晶，是传承中华民族优秀传统文化的经典载体。在习近平总书记系列重要讲话文章中多处引经据典，生动传神，寓意深邃，极具启迪意义。据此，人民日报社特别组织编写《习近平用典》一书，全书由敬民篇、为政篇、立德篇、修身篇、笃行篇、劝学篇、任贤篇、天下篇、廉政篇、信念篇、创新篇、法治篇、辩证篇共13个篇章组成，旨在对习近平总书记重要讲话（文章）引用典故追根溯源的同时，并究其现实意义进行解读，以期帮助广大党员干部深入学习习近平总书记的重要讲话（文章）精神，准确理解习近平总书记的思想精髓。', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (7, '平易近人：习近平的语言力量', '陈锡喜', '语言这东西，有种神奇的力量。习近平总书记在他的系列重要讲话中，常用打比方、讲故事的方式阐述深刻的道理，用大白话、大实话等俗文俚语来释疑解惑，用中国优秀文化传统元素来提纲挈领、纵横捭阖。总之，习近平总书记的语言，平实中蕴含着大智慧，更有一种透彻、直指人心的力量。比如“打‘老虎’、拍‘苍蝇’”、“把权力关进制度的笼子”，既形象又深刻，引来百姓一阵叫好。“打铁还需自身硬”、“有话要放到桌面上来讲”，简洁到位，生动有力！习近平总书记还经常引用一些古典诗词来阐述他的治国理念，如“治大国如烹小鲜”、“尚贤者，政之本也”。他用“浩渺行无极，扬帆但信风”来描述亚太共同的发展未来，用“虚谈废务”来强调理论联系实际的工作作风…本书从习近平总书记系列重要讲话的语言风格入手，辑录了中共十八大以来习近平总书记在各种场合发表的重要讲话中富有特色的引文引言，将其分为形象比喻、俗文俚语和诗文引用等四个篇章，对其语源、语义、理论价值以及社会反响等，进行简明扼要的阐释，以期为广大干部群众学习习近平总书记系列重要讲话精神，提供一个新颖的、可读性强的、“接地气”的读本。', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (13, '首先，打破一切常规-2', 'Marcus Buckingham   Curt Coffman', '这是一本管理人的人和被管理的人应当读的书。盖洛普公司的马库斯·白金汉和柯特·科夫曼对来自不同行来的大批优秀经理进行了深入研究，并在书中展示了他们重大发现。这些经理有的身居高位，有的任一线主管，有的供职《财富》五百强，有的在私人小公司效力。无论其处境如何，最终成为盖洛普研究重点的经理无一例外地善于变每个员工的才干为业绩。这本书在理念和内容上具有革命性，它说明为什么如此众多的传统观念和做法对当今的商务需求毫无帮助，同时提供一个简明而实用的模型，配以具体行动方案，帮助公司在生产效率、员工敬业度、顾客满意度和利润率上取得重大进步。而每个人首先应当是管理自己的人，这种管理包括你的思想、身体、金钱、才能、工作、情感、欲望和构成你生活的各种成份。在世界众多的顶级经理中，相互间的共同之处往往是不多的。他们间存在着性别、种族和年龄上的差异各有与众不同办事风格和持续关注目标。尽管这些优秀经理可谓千人千面，却有一处彼此相同：他们在动手做任何一件事之前，总要打破一切“传统”的陈规戒律。他们不相信，只要经过充分培训，一个人想做什么，就能做成什么。他们从来不试图帮助一个人克服他的弱点。他们一向不把金科玉律放在眼里。而且，由不得你不信，他们甚至有所偏爱。他们为什么这样？这本非同凡响的书将会告诉你。', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (14, '首先，打破一切常规-3', 'Marcus Buckingham   Curt Coffman', '这是一本管理人的人和被管理的人应当读的书。盖洛普公司的马库斯·白金汉和柯特·科夫曼对来自不同行来的大批优秀经理进行了深入研究，并在书中展示了他们重大发现。这些经理有的身居高位，有的任一线主管，有的供职《财富》五百强，有的在私人小公司效力。无论其处境如何，最终成为盖洛普研究重点的经理无一例外地善于变每个员工的才干为业绩。这本书在理念和内容上具有革命性，它说明为什么如此众多的传统观念和做法对当今的商务需求毫无帮助，同时提供一个简明而实用的模型，配以具体行动方案，帮助公司在生产效率、员工敬业度、顾客满意度和利润率上取得重大进步。而每个人首先应当是管理自己的人，这种管理包括你的思想、身体、金钱、才能、工作、情感、欲望和构成你生活的各种成份。在世界众多的顶级经理中，相互间的共同之处往往是不多的。他们间存在着性别、种族和年龄上的差异各有与众不同办事风格和持续关注目标。尽管这些优秀经理可谓千人千面，却有一处彼此相同：他们在动手做任何一件事之前，总要打破一切“传统”的陈规戒律。他们不相信，只要经过充分培训，一个人想做什么，就能做成什么。他们从来不试图帮助一个人克服他的弱点。他们一向不把金科玉律放在眼里。而且，由不得你不信，他们甚至有所偏爱。他们为什么这样？这本非同凡响的书将会告诉你。', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (15, '首先，打破一切常规-4', 'Marcus Buckingham   Curt Coffman', '这是一本管理人的人和被管理的人应当读的书。盖洛普公司的马库斯·白金汉和柯特·科夫曼对来自不同行来的大批优秀经理进行了深入研究，并在书中展示了他们重大发现。这些经理有的身居高位，有的任一线主管，有的供职《财富》五百强，有的在私人小公司效力。无论其处境如何，最终成为盖洛普研究重点的经理无一例外地善于变每个员工的才干为业绩。这本书在理念和内容上具有革命性，它说明为什么如此众多的传统观念和做法对当今的商务需求毫无帮助，同时提供一个简明而实用的模型，配以具体行动方案，帮助公司在生产效率、员工敬业度、顾客满意度和利润率上取得重大进步。而每个人首先应当是管理自己的人，这种管理包括你的思想、身体、金钱、才能、工作、情感、欲望和构成你生活的各种成份。在世界众多的顶级经理中，相互间的共同之处往往是不多的。他们间存在着性别、种族和年龄上的差异各有与众不同办事风格和持续关注目标。尽管这些优秀经理可谓千人千面，却有一处彼此相同：他们在动手做任何一件事之前，总要打破一切“传统”的陈规戒律。他们不相信，只要经过充分培训，一个人想做什么，就能做成什么。他们从来不试图帮助一个人克服他的弱点。他们一向不把金科玉律放在眼里。而且，由不得你不信，他们甚至有所偏爱。他们为什么这样？这本非同凡响的书将会告诉你。', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (16, '首先，打破一切常规-5', 'Marcus Buckingham   Curt Coffman', '这是一本管理人的人和被管理的人应当读的书。盖洛普公司的马库斯·白金汉和柯特·科夫曼对来自不同行来的大批优秀经理进行了深入研究，并在书中展示了他们重大发现。这些经理有的身居高位，有的任一线主管，有的供职《财富》五百强，有的在私人小公司效力。无论其处境如何，最终成为盖洛普研究重点的经理无一例外地善于变每个员工的才干为业绩。这本书在理念和内容上具有革命性，它说明为什么如此众多的传统观念和做法对当今的商务需求毫无帮助，同时提供一个简明而实用的模型，配以具体行动方案，帮助公司在生产效率、员工敬业度、顾客满意度和利润率上取得重大进步。而每个人首先应当是管理自己的人，这种管理包括你的思想、身体、金钱、才能、工作、情感、欲望和构成你生活的各种成份。在世界众多的顶级经理中，相互间的共同之处往往是不多的。他们间存在着性别、种族和年龄上的差异各有与众不同办事风格和持续关注目标。尽管这些优秀经理可谓千人千面，却有一处彼此相同：他们在动手做任何一件事之前，总要打破一切“传统”的陈规戒律。他们不相信，只要经过充分培训，一个人想做什么，就能做成什么。他们从来不试图帮助一个人克服他的弱点。他们一向不把金科玉律放在眼里。而且，由不得你不信，他们甚至有所偏爱。他们为什么这样？这本非同凡响的书将会告诉你。', '0', '0', '0', '开发二部', '2017-11-10 00:00:00', '党建', 1, 3, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (17, '首先，打破一切常规-6', 'Marcus Buckingham   Curt Coffman', '这是一本管理人的人和被管理的人应当读的书。盖洛普公司的马库斯·白金汉和柯特·科夫曼对来自不同行来的大批优秀经理进行了深入研究，并在书中展示了他们重大发现。这些经理有的身居高位，有的任一线主管，有的供职《财富》五百强，有的在私人小公司效力。无论其处境如何，最终成为盖洛普研究重点的经理无一例外地善于变每个员工的才干为业绩。这本书在理念和内容上具有革命性，它说明为什么如此众多的传统观念和做法对当今的商务需求毫无帮助，同时提供一个简明而实用的模型，配以具体行动方案，帮助公司在生产效率、员工敬业度、顾客满意度和利润率上取得重大进步。而每个人首先应当是管理自己的人，这种管理包括你的思想、身体、金钱、才能、工作、情感、欲望和构成你生活的各种成份。在世界众多的顶级经理中，相互间的共同之处往往是不多的。他们间存在着性别、种族和年龄上的差异各有与众不同办事风格和持续关注目标。尽管这些优秀经理可谓千人千面，却有一处彼此相同：他们在动手做任何一件事之前，总要打破一切“传统”的陈规戒律。他们不相信，只要经过充分培训，一个人想做什么，就能做成什么。他们从来不试图帮助一个人克服他的弱点。他们一向不把金科玉律放在眼里。而且，由不得你不信，他们甚至有所偏爱。他们为什么这样？这本非同凡响的书将会告诉你。', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (19, '精益思想（白金版）', 'James P.Womack[美]  Daniel T.Jones[英]', '本书的成功在于它对精益生产方式做了最好的总结，为读者提供了精益的核心原则，实地考察了美国、德国、日本若干具有代表性的大小企业推行精益的实际情况和心得，为准备跨入精益之门和进一步学习、实施精益的人提供了最好的指南，从而成为精益方面的经典著作。精益原则：根据客户需求，重新定义价值；识别价值流，重新制定企业活动；使价值流动起来；依靠客户需求拉动价值流；不断改善，追求尽善尽美', '0', '1', '0', '开发二部', '2017-11-10 00:00:00', '党建', 2, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (20, '用户故事与敏捷方法', 'Mike Cohn[美]', '本书详细介绍了用户故事与敏捷开发方法的结合，诠释了用户故事的重要价值，用户故事的实践过程，良好用户故事编写准则，如何搜集和整理用户故事，如何排列用户故事的优先级，进而澄清真正适合用户需求的、有价值的功能需求。本书对于软件开发人员、测试人员、需求分析师和管理者，具有实际的指导意义和重要的参考价值。', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (21, '敏捷教练：如何打造优秀的敏捷团队', 'Rachel Davies', '本书取材于国际知名敏捷教练的真实经历，展示了他们在辅导团队进行敏捷实践过程中所积累的辅导技巧，凝聚着他们在对敏捷辅导的真知灼见，每章还针对特定主题总结了在转型过程中教练和团队可能面对的障碍及其应对方案。本书具有较强的实用性和指导性，适合项目经理、技术总监和敏捷团队的所有成员阅读与参考。', '1', '0', '0', '开发二部', '2017-11-10 00:00:00', '技术', 1, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (22, 'Scrum敏捷软件开发', 'Mike Cohn[美]', '本书是敏捷联盟及Scrum联盟创始人之一、敏捷估算及计划的鼻祖Mike Cohn三大经典著作中影响最为深厚的扛鼎之作，也是全球敏捷社区中获得广泛肯定的企业敏捷转型权威参考。作者花四年时间，把自己近十五年的敏捷实践经验，特别是近四年中针对各种敏捷转型企业的咨询和指导工作，并结合旁征博引的方式，从更高的思想层次对敏捷与Scrum多年来的经验和教训进行深入而全面的梳理和总结，最终集大成者便是这本令人醍醐灌顶的佳作。本书是软件企业及其管理团队成功进行敏捷转型战略及实施的必备参考书，适合经理、开发人员、教练、Scrum Master、产品负责人、分析师、团队领导或项目领导，是帮助他们成功完成项目，甚至造就敏捷企业的重要参考。', '1', '0', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (23, 'Scrum精髓：敏捷转型指南', 'Kenneth Rubin', '短短几年时间，Scrum跃升为敏捷首选方法，在全球各地得以普遍应用。针对如何用好、用巧这个看似简单的框架，本书以通俗易懂的语言、条理清晰的脉络阐述和提炼出Scrum的精髓。全书共4部分23章，阐述了七大核心概念：Scrum框架，敏捷原则，冲刺，需求和用户故事，产品列表，估算与速率，技术债；三大角色：产品负责人，ScrumMaster，开发团队以及Scrum团队构成：Scrum规划原则及四大规划活动：多层级规划、产品组合规划、产品规划和长期规划；冲刺四大活动：规划、执行、评审和回顾。本书取自作者十多年的实践经验，对员工个体和管理层都具有重要的指导和参考意义，可以帮助企业顺利导入Scrum，在动态的商业环境中以积极心态拥抱变化，做出优秀、卓越的产品，走上创业、守业、常青基业的成功之路。', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (24, 'Scrum敏捷产品管理：打造用户喜爱的产品', 'Roman Pichler[德]', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (25, 'Scala学习手册', 'Jason Swartz', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (26, '数据算法：Hadoop/Spark大数据处理技巧', 'Mahmoud Parsian', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (27, 'HBase权威指南', 'Lars George', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (28, '神经网络与机器学习', 'Simon Haykin', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (29, '高性能Scala', 'Vincent Theron   Michael Diamant[美]', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (30, 'Python机器学习实践指南', 'Alexander T.Combs', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (31, '机器学习', '周志华', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (32, '深度学习', 'Ian Goodfellow [美]   Yoshua Bengio[加]  Aaron Courville[加]', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (33, '机器学习：贝叶斯和优化方法', 'Sergios Theodoridis', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (34, 'Spark快速数据处理', 'Holden Karau', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (35, 'Ceph分布式存储实战', 'Ceph中国社区', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (36, 'Redis开发与运维', '付磊、张益军', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (37, 'Storm分布式实时计算模式', 'P.Taylor Goetz[美]     Brain O\'Neil[美]', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (38, '快学Scala', 'Cay S.Horstmann', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (39, '概率编程实战', 'Avi Pfeffer[美]', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (40, 'Python机器学习及实践', '范淼、李超', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (41, 'Shell脚本学习指南', 'Arnold Robbins   Nelson H.F.Beebe[美]', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 1, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (42, 'Python Cookbook（第3版）中文版', 'David Beazley      Brain K.Jones[美]', '图书简介', '1', '0', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (43, 'Scala程序设计', 'Dean Wampler     Alex Payne', '图书简介', '1', '1', '0', '开发二部', '2017-11-10 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (46, '团队管理49个细节', '吕国荣', '图书简介', '0', '1', '1', '刁圣凤', '2017-12-01 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (47, '银行转型2025', '何大勇、谭彦、陈本强、刘月', '图书简介', '0', '0', '1', '刁圣凤', '2017-12-01 00:00:00', '党建', 1, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (48, '习近平的七年知青岁月', '中央党校采访实录编辑室', '图书简介', '0', '1', '1', '刁圣凤', '2017-12-01 00:00:00', '党建', 5, 1, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (49, '大数据（3.0升级版）', '涂子沛', '图书简介', '0', '1', '1', '米坤明', '2017-12-25 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (50, '知行合一（实现价值驱动的敏捷和精益开发）', '丛斌', '图书简介', '0', '1', '1', '李朋', '2017-12-25 00:00:00', '党建', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (53, '分布式Java应用基础与实践', '林昊', '图书简介', '1', '1', '1', '雷锋', '2018-01-01 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (54, '垃圾回收的算法和实现', '中村成洋 相川光[日]', '图书简介', '1', '1', '1', '雷锋', '2018-01-01 00:00:00', '技术', 0, 0, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (55, '智能密码钥匙：原理、技术及应用', '飞天诚信科技股份有限公司', '图书简介', '1', '1', '1', '雷锋', '2018-01-01 00:00:00', '技术', 0, 1, '2018-01-18 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (57, '党务工作者实用手册', '本书编写组', '图书简介', '0', '1', '0', '开发二部', '2018-01-23 00:00:00', '党建', 0, 0, '2018-02-09 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (58, '打铁还需自身硬', '曾峻 朱高亮', '图书简介', '0', '1', '0', '开发二部', '2018-01-23 00:00:00', '党建', 2, 0, '2018-02-09 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (59, '全面从严治党面对面', '中共中央宣传部理论局', '图书简介', '0', '1', '0', '开发二部', '2018-01-23 00:00:00', '党建', 0, 0, '2018-02-09 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (60, '全面从严治党', '中共中央组织部研究室（政策法规局）', '图书简介', '0', '1', '0', '开发二部', '2018-01-23 00:00:00', '党建', 0, 0, '2018-02-09 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (61, '基层党组织组织生活创新案例', '中共北京市委组织部           中共北京市委《支部生活》杂志社', '图书简介', '0', '1', '0', '开发二部', '2018-01-23 00:00:00', '党建', 0, 0, '2018-02-09 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (62, '基层党组织组织生活创新案例', '中共北京市委组织部           中共北京市委《支部生活》杂志社', '图书简介', '0', '1', '0', '开发二部', '2018-01-23 00:00:00', '党建', 0, 0, '2018-02-09 00:00:00', 0);
INSERT INTO `book_baseinfo` VALUES (63, 'Java与XML', 'Brett McLaugblin', '本书第二版还包括SAX和DOM的高级知识章节，以及关于SOAP和数据绑定的新章节。在介绍XML基础的一章之后，本书的其他部分着眼于如何在Java应用中使用XML。如果你在使用Java进行开发并需要使用XML，或者以后要使用XML，或者你正投身于新的P2P运动、消息收发或Web服务，或者在为电子商务开发软件，本书将是你不可或缺的伴侣。一段时间以来，XML已经成为Inter开发人员之间的热门话题。但是怎样深入理解XML并让其工作呢？本书演示了如何使用XML API、工具和各种技巧构建真实世界的应用，并且要求这些代码和数据都是真正可移植的。 Java为编程提供了一种与平台无关的语言，从而导致了一场编程世界的革命。XML为数据交换提供了一种与平台无关的语言，使得这场革命更进一步。对于构建基于Web的企业级应用而言，Java和XML有许多相似的特性，比如平台无关性、可扩展性、可重用性和全球语言（Unicode）的支持。本书展示了如何将两者结合起来构建动态生成内容的Web站点，如何编写具有更低开销的信息共享和数据交换的企业级软件，以及如何对需要可移植数据的其他问题开发简单和高效的解决方案。本书覆盖如下主题：XML基础，包括DTD、名字空间、XML Schema、XPath和XSL；SAX API，包括所有的处理类、SAX2扩展、过滤器和写入器；DOM API，包括DOM Level3和DOM HTML模块；OM API，包括API核心和XPath支持；数据绑定，使用DTD和XML Schema作为约束； 使用XML-RPC与SOAP开发应用；使用Web发布框架，如Apache Cocoon；使用SOAP、UDDI和WSDL创建Web服务；使用RSS和XSP构建信息频道和动态内容。', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 0, '2018-03-14 12:26:40', 0);
INSERT INTO `book_baseinfo` VALUES (65, 'C#与.NET3.5高级程序设计(第4版）', 'Andrew Troelsen', '本书是C#领域久负盛名的经典著作，深入全面地叙述了C#编程语言和.NET平台核心，并以大量示例剖析相关概念。书中介绍了C#的各种语言构造、.NET 2.0的类、核心API、公共中间语言(CIL)、动态程序集和ASP.NET扩展等内容；同时也介绍了.NET 3.0和.NET 3.5中新的编程API，包括WPF 、WCF和WF 的功能；另外，还介绍了最新的C# 3.0编程语言、LINQ编程技术、COM与.NET 的互操作性以及平台无关的.NET开发', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 0, '2018-03-14 12:30:33', 0);
INSERT INTO `book_baseinfo` VALUES (66, 'CSS网站布局实录（第2版）', '李超', '国内第1本基于Web标准的CSS布局著作技术增值升级版!讲述基于Web标准的应用CSS进行网站布局设计与重构的典范之作!本书特色：1、知识全面、完美应用：CSS选择器、样式继承、层叠、格式化、XML标签、CSS滤镜等。文本、图像、超链接、列表、菜单、网站导航、表单、数据表格、浮动布局等CSS布局控制。2、智能、创造型布局思维：智慧、完美视觉艺术效果之CSS布局技巧、CSS Hack实践战略。3、Web技术团队倾情奉献：来自CSS布局与Web标准应用之先驱——闪客帝国的Web应用技术团队。顶尖Web设计师和Web应用开发人员的完整经验、技术倾囊奉献。4、国外网站重构经典作品引进、拓展：Web 2.0标准与CSS重构技术——国外经典之作结合之典范。', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 0, '2018-03-14 12:31:33', 0);
INSERT INTO `book_baseinfo` VALUES (67, 'POJOs IN ACTION中文版', 'Chris Richardson', '一本实践指南，它围绕POJO（普通Java对象）构造了一个完整的应用程序，对框架之间的各种组合进行了详细的举例说明；描述了在采用POJO和轻量级框架如Hibernate、Spring时，如何制定主要设计决策，包括怎样组织和封装业务逻辑、访问数据库、管理事务以及如何处理数据库并发等。此外，本书还详尽地分析了事务管理、悲观锁、乐观锁、条件组合搜索等难点问题。', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 0, '2018-03-14 12:34:15', 0);
INSERT INTO `book_baseinfo` VALUES (68, 'AIX系统管理与网络管理', 'UNIX管理系列 编委会', '暂无简介', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 1, 1, '2018-03-14 12:36:01', 0);
INSERT INTO `book_baseinfo` VALUES (69, 'iBATIS实战', 'Clinton Begin,Brandon Goodin,Larry Meadors ', '本书是讲述iBATIS框架的权威著作。书中既详实地介绍了iBATIS的设计理念和基础知识，也讨论了动态SQL、高速缓存、DAD框架等高级主题，还讲解了iBATIS在实际开发中的应用。本书的最后给出了一个设计优雅、层次清晰的示例程序JGameStore，该示例涵盖全书的大部分知识点，可以作为iBATIS学习和Web开发的经典案例，非常值得深入研究。', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 0, '2018-03-14 12:37:57', 0);
INSERT INTO `book_baseinfo` VALUES (70, 'AIX 5L管理指南', 'Randal K.michaael', '暂无简介', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 0, '2018-03-14 12:39:07', 0);
INSERT INTO `book_baseinfo` VALUES (71, 'Java Servlet&JSP经典实例', 'Bruce W.perry\r\n', '本书将用于帮助指导Java web开发人员的日常任务，提供典型的web相关问题的快速解决方案。本书集中介绍了如何用Java初始化某些与web相关的任务，而不是教会读者如何使用Java语言，或者事无巨细地解释servlet和JSP API。书中包含了大量关于复杂的日常开发任务的技巧，这些技巧涵盖了许多与Servlet 2.4和JSP 2.0规范相关联的新特性，包括ServletRequestListener、新的JSTL 1.1函数、使用模板文本中的JSTL元素、标记文件和基于XML Schema的部署描述文件。', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 0, '2018-03-14 12:40:10', 0);
INSERT INTO `book_baseinfo` VALUES (72, 'Spring专业开发指南', 'Rob Harrop ,Jan Machacek', '不仅对Spring MVC的应用进行了大篇幅的探讨，同时与其它Spring相关书籍不同，本书还对Spring的远程访问技术以及任务管理机制进行了深入介绍，突出Spring作为轻量级框架的特征的同时，还将Spring与传统J2EE开发方式进行了完美的结合。本书附录中还介绍了Spring项目的测试以及客户端开发，展示了Spring IDE的使用方法，以及Spring未来会面对的一些技术变革。本书覆盖了Spring开发的各个环节，同时也为程序员进行Spring相关开发工作提供了技术细节的指导，是适合各种层次Java程序员的Spring宝典。', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 0, '2018-03-14 12:41:14', 0);
INSERT INTO `book_baseinfo` VALUES (73, 'UML与系统分析设计（第二版）', '张龙祥', '主要介绍UML以及UML在面向对象的软件系统分析和设计中的应用。本修订版本增加介绍新标准UML2.0的一些有关变更，以及UML在数据库设计和Web应用系统设计中的应用等内容。本书理论与实际结合，既有UML的概念、结构、语义与表示法的介绍，又有具体的应用示例，着重实用性和可操作性，叙述深入浅出，便于学以致用。本书可作为计算机相关专业的大专院校教材或高级软件工程培训班教材，也是一本通用的技术参考书，适合大专院校有关专业的师生、计算机项目管理人员与计算机软件开发人员使用。', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 0, '2018-03-14 12:42:10', 0);
INSERT INTO `book_baseinfo` VALUES (74, 'Java JDK 5.0学习笔记', '良葛格', '本书是作者良葛格本人近几年来学习Java的心得笔记，结构按照作者的学习脉络依次展开，从什么是Java、如何配置Java开发环境、基本的Java语法到程序流程控制、管理类文件、异常处理、枚举类型、泛型、J2SE中标准的API等均进行了详细介绍。本书的最后一章还安排了一个“文字编辑器”的专题制作。本书门槛很低，只要您了解Windows基本操作，无需有其他程序语言的基础，甚或没有接触过Java，都可以通过本书扎扎实实地学习Java。而对于一些曾经学习过Java，但概念和基础仍不扎实的读者，本书也准备了一些面向对象的观点与进阶的议题可供参考。', '1', '1', '1', '周建国', '2018-02-08 12:00:00', '技术', 0, 1, '2018-03-14 12:43:14', 0);
INSERT INTO `book_baseinfo` VALUES (75, '深入浅出Struts', 'Budi Kurniawan', '本书是世界畅销的Struts著作之一，深入浅出地探讨了许多能帮助程序员们编写高效Struts应用程序的技巧，主要内容包括利用动作表单进行输入验证、HTML 标签库、输入验证和数据转换、Validator 插件、表达式语言、JSTL 库、Bean 标签库、Logic 标签库、消息处理与国际化、Tiles 框架等Struts 功能，最后还直接剖析源代码，深入阐述了Struts 的工作机理。书中概念清晰，环环相扣，便于读者学习。', '1', '1', '1', '周建国\r\n', '2018-02-08 12:00:00', '技术', 1, 0, '2018-03-14 12:44:22', 0);
INSERT INTO `book_baseinfo` VALUES (76, '社群思维', '付岩', '当今时代，人类已经置身于一个技术化生存的世界之中，在一个技术已经“无所不能”的时代中，人类对其产生了巨大的依赖，但技术化已经带来一个严重的后果，就是技术对人的控制和支配越来越明显，人的真性情正在逐渐被技术所吞噬。人类在科学技术面前越来越缺乏理性，而当人类面对如此的困境时，我们该何去何从？付岩认为，社群思维是人性化生存法则，是一种关乎生存、关于价值观的思维方式，它强调社群对于个人和企业的重要性。本书开创性地提出塑造魅力人格体是人生的根本任务，对处在“大众创业、万众创新”时代的创业者尤其如此。在功能商业时代缓缓落幕，精神商业时代大幕开启的大背景下，创业者、企业家、投资人乃至普通人，如何运用有效社群这一概念，在创新创业及至日常生活中塑造完美的魅力人格体，是本书所要解决的难题。什么是真正的社群？哪些是有效社群？社群的本质到底是什么？社群思维可以为创业者解决哪些创业难题？给企业家怎样的启发？给投资人哪些投资方法论？给普通人的人生怎样的反思？本书对这些问题进行了深入的阐述。', '0', '1', '1', '刁圣凤', '2018-02-08 12:00:00', '党建', 1, 1, '2018-03-14 12:45:45', 0);
INSERT INTO `book_baseinfo` VALUES (77, '向云环境迁移', '托比尔斯·哈沃斯', '大数据浪潮汹涌来席，然而，所谓大数据改变你的生活仅仅是概念的偷换。本书告诉你，没有云环境，就没有大数据！从网购到QQ聊天，从电话订餐到手机刷屏，从预约业务到持卡消费，生活与人际的数字化、网络化、商业化的尝试早已开始。乔布斯的苹果以独特的“云策略”在智能手机领域独领风骚，日本乐天通过统一的云平台存储建立了世界模式的“乐天经济圈”，国内两大电商巨鳄马云与马化腾开始基于云商平台对中国未来商业进行跑马圈地……隐私与风险，商机与决策，规范与政府导向……未来之争是一场信息之争，本书与你分享云经济领域前沿信息，让你跻身赢家行列！', '1', '0', '1', '代威', '2018-02-24 12:00:00', '技术', 2, 2, '2018-03-14 12:47:00', 0);
INSERT INTO `book_baseinfo` VALUES (78, '互联网进化论', '刘锋', '通过互联网进化论的提出，作者将云计算，物联网、移动互联网与传统互联网有机地结合在一起。本书全方位地介绍了互联网的技术要点和商业模式，深入探讨了互联网的未来发展趋势。本书中提出互联网将沿着九条规律进化成与人类大脑相似的组织结构，同时互联网也将导致神经学在21世纪获得重大突破。本书作者详细阐述了人脑中也存在goolge、ip地址、微博、维基百科、路由系统的实证案例和实验设计方法。本书可以帮助互联网爱好者全面了解互联网的发展历史、演化规律和未来趋势，也可以供云计算、物联网、移动互联网、科技哲学和神经学领域的研究者参考。', '1', '0', '0', '开发二部', '2018-02-24 12:00:00', '技术', 2, 3, '2018-03-14 12:48:16', 0);
INSERT INTO `book_baseinfo` VALUES (79, '企业IT架构转型之道：阿里巴巴中台战略思想与架构实战', '钟华', NULL, '1', '0', '0', '开发二部', '2018-06-22 17:17:31', '技术', 0, 0, '2018-07-06 17:17:56', 0);
INSERT INTO `book_baseinfo` VALUES (80, '企业IT架构转型之道：阿里巴巴中台战略思想与架构实战', '钟华', NULL, '1', '0', '0', '开发二部', '2018-06-22 17:21:40', '技术', 2, 7, '2018-07-06 17:21:57', 0);
INSERT INTO `book_baseinfo` VALUES (81, '习近平总书记系列重要讲话读本（2016版）', '中共中央宣传部', NULL, '0', '1', '0', '开发二部', '2018-07-06 17:23:01', '党建', 0, 0, '2018-07-06 17:23:09', 0);
INSERT INTO `book_baseinfo` VALUES (82, '习近平谈治国理政（第一卷）', '', NULL, '0', '1', '0', '开发二部', '2018-07-06 17:23:48', '党建', 0, 0, '2018-07-06 17:23:52', 0);
INSERT INTO `book_baseinfo` VALUES (83, '习近平谈治国理政（第二卷）111', '', NULL, '0', '1', '0', '开发二部', '2018-07-06 17:24:23', '党建', 0, 0, '2018-07-06 17:24:31', 0);

-- ----------------------------
-- Table structure for book_borrowrecord
-- ----------------------------
DROP TABLE IF EXISTS `book_borrowrecord`;
CREATE TABLE `book_borrowrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `bookName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书名',
  `borrowStatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图书借还状态:0：借出；1：归还',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `headImgUrl` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信头像Url',
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `borrowTime` datetime(0) DEFAULT NULL COMMENT '借出时间',
  `returnTime` datetime(0) DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_bookId`(`bookId`) USING BTREE,
  INDEX `idx_openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书借阅记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_borrowrecord
-- ----------------------------
INSERT INTO `book_borrowrecord` VALUES (1, 21, '敏捷教练：如何打造优秀的敏捷团队', '0', 'oe0Ej0Zs4ZyseKAz39Px3VtcS36g', '', '郭海梁', '2018-01-22 09:50:11', NULL);
INSERT INTO `book_borrowrecord` VALUES (2, 8, '未来简史-1', '1', 'oe0Ej0WDAe0Y2UFn1_jz8GEB2MFU', '', '高雅', '2018-01-22 10:24:37', '2018-01-22 10:29:07');
INSERT INTO `book_borrowrecord` VALUES (3, 37, 'Storm分布式实时计算模式', '1', 'oe0Ej0Z5nLgZJUl1z1fDB7TUttkU', '', '朱莹', '2018-01-22 13:02:45', '2018-01-30 13:06:12');
INSERT INTO `book_borrowrecord` VALUES (4, 8, '未来简史-1', '1', 'oe0Ej0QDKVSRHFXZH3ZiDYt_wRAU', '', '李冰', '2018-01-22 18:32:24', '2018-01-23 11:14:13');
INSERT INTO `book_borrowrecord` VALUES (5, 36, 'Redis开发与运维', '1', 'oe0Ej0Q-0npwv_4_3A-3EIAoIKUs', '', '张威', '2018-01-26 09:21:02', '2018-07-06 09:31:33');
INSERT INTO `book_borrowrecord` VALUES (6, 34, 'Spark快速数据处理', '1', 'oe0Ej0Q-0npwv_4_3A-3EIAoIKUs', '', '张威', '2018-01-26 09:21:29', '2018-01-30 10:27:36');
INSERT INTO `book_borrowrecord` VALUES (7, 25, 'Scala学习手册', '1', 'oe0Ej0Q-0npwv_4_3A-3EIAoIKUs', '', '张威', '2018-01-26 09:21:51', '2018-07-06 09:31:43');
INSERT INTO `book_borrowrecord` VALUES (8, 48, '习近平的七年知青岁月', '1', 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '2018-01-27 20:30:34', '2018-02-04 14:20:55');
INSERT INTO `book_borrowrecord` VALUES (9, 7, '平易近人：习近平的语言力量', '1', 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '2018-02-12 14:06:12', '2018-06-11 08:19:57');
INSERT INTO `book_borrowrecord` VALUES (10, 5, '浴血荣光', '1', 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '2018-02-12 14:06:29', '2018-04-10 13:51:22');
INSERT INTO `book_borrowrecord` VALUES (11, 50, '知行合一（实现价值驱动的敏捷和精益开发）', '1', 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '2018-02-12 14:47:29', '2018-06-26 16:15:39');
INSERT INTO `book_borrowrecord` VALUES (12, 47, '银行转型2025', '0', 'oe0Ej0Q3v0JtZkcFlwz6cu6jOSeQ', '', '宋承谦', '2018-02-23 17:09:32', NULL);
INSERT INTO `book_borrowrecord` VALUES (13, 19, '精益思想（白金版）', '1', 'oe0Ej0S9Vsp0D6a4XNEnOnyKuOVU', '', '李朋', '2018-02-26 14:10:51', '2018-07-06 10:22:50');
INSERT INTO `book_borrowrecord` VALUES (14, 22, 'Scrum敏捷软件开发', '0', 'oe0Ej0eLxayk9IyeDOrhF4zzJJaE', '', '张雅斯', '2018-02-26 16:03:19', NULL);
INSERT INTO `book_borrowrecord` VALUES (15, 48, '习近平的七年知青岁月', '1', 'oe0Ej0WtcgZ6xEIdfwyKeY2keSHc', '', '杨蕾', '2018-03-02 13:59:20', '2018-03-02 14:02:18');
INSERT INTO `book_borrowrecord` VALUES (16, 48, '习近平的七年知青岁月', '1', 'oe0Ej0WtcgZ6xEIdfwyKeY2keSHc', '', '杨蕾', '2018-03-02 14:12:09', '2018-03-02 14:12:15');
INSERT INTO `book_borrowrecord` VALUES (17, 2, '中国触动', '1', 'oe0Ej0QqaxWowCmmjXoE9McC7NsQ', '', '乔冬亮', '2018-03-02 14:14:22', '2018-03-02 14:18:20');
INSERT INTO `book_borrowrecord` VALUES (18, 2, '中国触动', '1', 'oe0Ej0QqaxWowCmmjXoE9McC7NsQ', '', '乔冬亮', '2018-03-02 14:41:52', '2018-03-05 14:47:22');
INSERT INTO `book_borrowrecord` VALUES (19, 42, 'Python Cookbook（第3版）中文版', '0', 'oe0Ej0RNFxxm692DTryk_wCh614s', '', '霍雨佳', '2018-03-22 14:25:09', NULL);
INSERT INTO `book_borrowrecord` VALUES (20, 41, 'Shell脚本学习指南', '1', 'oe0Ej0XKpLLh8wn1UZDnWTGQ4NXM', '', '高勇', '2018-04-12 10:00:46', '2018-04-12 10:03:07');
INSERT INTO `book_borrowrecord` VALUES (21, 55, '智能密码钥匙：原理、技术及应用', '1', 'oe0Ej0XKpLLh8wn1UZDnWTGQ4NXM', '', '高勇', '2018-04-12 10:01:12', '2018-04-12 10:02:00');
INSERT INTO `book_borrowrecord` VALUES (22, 73, 'UML与系统分析设计（第二版）', '1', 'oe0Ej0XKpLLh8wn1UZDnWTGQ4NXM', '', '高勇', '2018-04-12 10:02:42', '2018-04-12 10:02:56');
INSERT INTO `book_borrowrecord` VALUES (23, 74, 'Java JDK 5.0学习笔记', '1', 'oe0Ej0QqaxWowCmmjXoE9McC7NsQ', '', '乔冬亮', '2018-05-03 15:10:01', '2018-05-03 15:10:27');
INSERT INTO `book_borrowrecord` VALUES (24, 2, '中国触动', '1', 'oe0Ej0QqaxWowCmmjXoE9McC7NsQ', '', '乔冬亮', '2018-05-08 19:49:44', '2018-05-08 19:50:08');
INSERT INTO `book_borrowrecord` VALUES (25, 78, '互联网进化论', '1', '7158', '', '李驰', '2018-05-11 18:59:40', '2018-05-11 18:59:54');
INSERT INTO `book_borrowrecord` VALUES (26, 78, '互联网进化论', '1', '7109', '', '吴书迪', '2018-05-11 19:14:07', '2018-05-13 05:53:34');
INSERT INTO `book_borrowrecord` VALUES (27, 58, '打铁还需自身硬', '1', '0088', '', '姜燕', '2018-05-12 00:16:36', '2018-05-14 11:26:26');
INSERT INTO `book_borrowrecord` VALUES (28, 16, '首先，打破一切常规-5', '0', '3197', '', '高勇', '2018-05-12 23:08:55', NULL);
INSERT INTO `book_borrowrecord` VALUES (29, 77, '向云环境迁移', '0', '0088', '', '姜燕', '2018-05-14 11:26:13', NULL);
INSERT INTO `book_borrowrecord` VALUES (30, 72, 'Spring专业开发指南', '1', 'oe0Ej0besxqth6muj72ZzfYGmMp0', '', '黄实', '2018-06-19 21:17:17', '2018-06-19 21:19:33');
INSERT INTO `book_borrowrecord` VALUES (31, 23, 'Scrum精髓：敏捷转型指南', '1', 'oe0Ej0c8zOzx1lEPEwOSCexRkr6c', '', '张旭', '2018-07-03 14:57:29', '2018-07-03 14:59:23');
INSERT INTO `book_borrowrecord` VALUES (32, 80, '企业IT架构转型之道：阿里巴巴中台战略思想与架构实战', '0', 'oe0Ej0Vo9XYoN93z5ACOi7Y-xWU0', '', '李渤', '2018-07-10 11:01:07', NULL);
INSERT INTO `book_borrowrecord` VALUES (33, 79, '企业IT架构转型之道：阿里巴巴中台战略思想与架构实战', '0', 'oe0Ej0VbUk3RqwD-Wo9jOK0Fznz4', '', '冯欢', '2018-07-13 12:37:51', NULL);
INSERT INTO `book_borrowrecord` VALUES (36, 78, '互联网进化论', '1', 'oe0Ej0besxqth6muj72ZzfYGmMp0', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHvy8Tuqkep87yztwR6SWkqY2JYBqVsaj7b4h7XH3ZCd7KWCZoLosLIA5C5uqmJpkhm55Y3R4mxg/0', '黄实', '2018-07-16 20:48:35', '2018-07-16 20:49:37');
INSERT INTO `book_borrowrecord` VALUES (37, 82, '习近平谈治国理政（第一卷）', '1', 'oe0Ej0besxqth6muj72ZzfYGmMp0', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHvy8Tuqkep87yztwR6SWkqY2JYBqVsaj7b4h7XH3ZCd7KWCZoLosLIA5C5uqmJpkhm55Y3R4mxg/0', '黄实', '2018-07-16 22:23:41', '2018-07-16 22:23:48');
INSERT INTO `book_borrowrecord` VALUES (38, 82, '习近平谈治国理政（第一卷）', '1', 'oe0Ej0besxqth6muj72ZzfYGmMp0', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHvy8Tuqkep87yztwR6SWkqY2JYBqVsaj7b4h7XH3ZCd7KWCZoLosLIA5C5uqmJpkhm55Y3R4mxg/0', '黄实', '2018-07-16 22:23:53', '2018-07-16 22:24:02');
INSERT INTO `book_borrowrecord` VALUES (39, 78, '互联网进化论', '0', 'oe0Ej0besxqth6muj72ZzfYGmMp0', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHvy8Tuqkep87yztwR6SWkqY2JYBqVsaj7b4h7XH3ZCd7KWCZoLosLIA5C5uqmJpkhm55Y3R4mxg/0', '黄实', '2018-07-18 18:41:45', NULL);

-- ----------------------------
-- Table structure for book_commentrecord
-- ----------------------------
DROP TABLE IF EXISTS `book_commentrecord`;
CREATE TABLE `book_commentrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `headImgUrl` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信头像Url',
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '评论内容',
  `recTime` datetime(0) NOT NULL COMMENT '评论时间',
  `commentLikeNum` int(11) NOT NULL DEFAULT 0 COMMENT '评论点赞数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_bookId`(`bookId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书评论记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_commentrecord
-- ----------------------------
INSERT INTO `book_commentrecord` VALUES (1, 48, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '党员干部锤炼党性的生动范本\n　人都有自己的青春时代。每一个的人生，除了所处的不同客观环境、客观条件影响外，很大程度上与青年时代的主观世界、主体努力与人格状态不同直接相关。习近平同志之所以在今天能担任我们党的总书记，应当说，与他在陕北延安梁家河七年知青岁月的艰辛磨炼、积极努力，以及之后的历练密切相关。《习近平的七年知青岁月》将习近平知青岁月的整体形象，清晰地展现在我们面前了。', '2018-01-21 15:25:15', 0);
INSERT INTO `book_commentrecord` VALUES (2, 55, 'oe0Ej0XKpLLh8wn1UZDnWTGQ4NXM', '', '高勇', '好', '2018-04-12 10:01:49', 0);
INSERT INTO `book_commentrecord` VALUES (3, 78, '0088', '', '姜燕', '好书', '2018-05-11 18:26:50', 0);
INSERT INTO `book_commentrecord` VALUES (4, 77, '0088', '', '姜燕', '123', '2018-05-11 18:28:51', 0);
INSERT INTO `book_commentrecord` VALUES (5, 76, '7211', '', '胡小磊', '棒', '2018-05-11 19:12:12', 0);
INSERT INTO `book_commentrecord` VALUES (6, 78, '0088', '', '姜燕', '好书', '2018-05-12 00:14:32', 0);
INSERT INTO `book_commentrecord` VALUES (7, 16, '3197', '', '高勇', '哈哈😄 ', '2018-05-12 23:08:12', 0);
INSERT INTO `book_commentrecord` VALUES (8, 16, '3197', '', '高勇', '一样', '2018-05-12 23:08:33', 0);
INSERT INTO `book_commentrecord` VALUES (9, 16, '3197', '', '高勇', 'uu', '2018-05-12 23:08:45', 0);
INSERT INTO `book_commentrecord` VALUES (10, 68, '3197', '', '高勇', '试试', '2018-05-13 17:19:48', 0);
INSERT INTO `book_commentrecord` VALUES (11, 74, '0088', '', '姜燕', '好书', '2018-05-14 12:27:28', 0);
INSERT INTO `book_commentrecord` VALUES (12, 80, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '本书介绍了阿里巴巴中台战略的原因，架构演变过程，同时介绍了共享服务中心搭建原则，技术选型，数据拆分，高可用和高并发技术等。\n序言\n·最大的浪费不是重复建设，而是不断重复建设。\n·要从生产型模型升级到运营型模型，从版本模型升级到迭代模型。运营型模型最大的优势是所有的积累都被沉淀，而生产型模型会因为10%的差异而重新建设100%的系统。', '2018-07-06 21:07:23', 0);
INSERT INTO `book_commentrecord` VALUES (13, 80, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '第一章阿里巴巴中台战略\n构建更具创新性、灵活性的“大中台、小前台”组织机制和业务机制，作为前台的一线业务会更敏捷、更快速适应瞬息万变的市场，而中台将集合整个集团的运营数据能力、产品技术能力，对各前台业务形成强力支撑。即“厚平台，薄应用”架构。\n对业务的下一步发展有着自己的理解和看法，对业务流程如何进一步优化来更好地提升业务，对企业现有的业务提出创新的想法，为企业带来新的业务增长点。才叫懂业务。', '2018-07-06 21:28:43', 0);
INSERT INTO `book_commentrecord` VALUES (14, 80, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', 'o    服务最不需要“业务稳定”。一个服务如果一味追求功能的不变，一定程度上就是固步自封，逼其他业务系统重复造轮子。\no    服务需要不停的滋养，只有在滋养中才能从最初仅提供单薄业务功能的服务逐渐成长为企业最为宝贵的IT资产，而服务所需的滋养正是来自新业务的不断进行服务接入。最终使这些服务变得更加专业和稳定。\n小前端团队', '2018-07-07 22:00:56', 0);
INSERT INTO `book_commentrecord` VALUES (15, 80, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '•中心化服务框架解决的是异构系统之间的交互，去中心化服务框架解决的是系统扩展。\n•去中心化服务框架基于统一的技术接口标准、网络协议、规范（多版本支持、负载均衡等）进行交互，服务契约先行的方式进行服务接口功能的约定，保障了服务接口和稳定性。\n•真正最佳的共享服务是将共性业务下沉为共享组件（即各个中心），再由共享组件暴露共享服务。即先做共享组件下沉，再做共享服务暴露。', '2018-07-08 18:08:55', 0);
INSERT INTO `book_commentrecord` VALUES (16, 80, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '共享服务中心的架构目的：通过业务拆分，降低系统复杂性；通过服务共享，提供可重用性；通过服务化，达到业务支持的敏捷性；通过统一的数据架构，消除数据交互的屏障。\n期望服务中心是承载业务逻辑、沉淀业务数据、产生业务价值的业务单元。\nIT项目的实施沉淀什么样的IT资产？真正最佳的资产就是中台的各个共享组件和共享服务，即包含了数据+业务逻辑两个方面重要内容的服务中心。', '2018-07-10 21:33:19', 0);
INSERT INTO `book_commentrecord` VALUES (17, 80, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '', '刁凤圣', '解决数据库瓶颈手段：读写分离，分库分表。\n·  数据尽可能平均拆分\n·  尽量减少事务边界，即单个SQL语句在后端数据库上同时执行的数量\n·  利用异构索引表尽量降低全表扫描频率，采用异步机制，将原表内的每一次创建或更新，换另一个维度保存一份完整的数据表或索引表，“用空间换时间”\n·  多条件频繁查询引入搜索引擎平台\n·  简单就是美。仅针对80%情况下访问的20%场景进行异构索引。', '2018-07-11 23:12:35', 0);
INSERT INTO `book_commentrecord` VALUES (20, 78, 'oe0Ej0besxqth6muj72ZzfYGmMp0', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHvy8Tuqkep87yztwR6SWkqY2JYBqVsaj7b4h7XH3ZCd7KWCZoLosLIA5C5uqmJpkhm55Y3R4mxg/0', '黄实', 'lklkl😂', '2018-07-16 12:49:01', 0);
INSERT INTO `book_commentrecord` VALUES (21, 77, 'oe0Ej0besxqth6muj72ZzfYGmMp0', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHvy8Tuqkep87yztwR6SWkqY2JYBqVsaj7b4h7XH3ZCd7KWCZoLosLIA5C5uqmJpkhm55Y3R4mxg/0', '黄实', '啦啦啦', '2018-07-16 14:32:18', 0);
INSERT INTO `book_commentrecord` VALUES (22, 80, 'oe0Ej0besxqth6muj72ZzfYGmMp0', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHvy8Tuqkep87yztwR6SWkqY2JYBqVsaj7b4h7XH3ZCd7KWCZoLosLIA5C5uqmJpkhm55Y3R4mxg/0', '黄实', '评论内容', '2018-07-18 11:07:49', 0);
INSERT INTO `book_commentrecord` VALUES (23, 1, 'oe0Ej0besxqth6muj72ZzfYGmMp0', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHvy8Tuqkep87yztwR6SWkqY2JYBqVsaj7b4h7XH3ZCd7KWCZoLosLIA5C5uqmJpkhm55Y3R4mxg/0', '黄实', 'hehe😄', '2018-07-24 06:39:51', 0);

-- ----------------------------
-- Table structure for book_likerecord
-- ----------------------------
DROP TABLE IF EXISTS `book_likerecord`;
CREATE TABLE `book_likerecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookId` int(11) NOT NULL COMMENT '图书编号',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `recTime` datetime(0) NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书点赞记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_likerecord
-- ----------------------------
INSERT INTO `book_likerecord` VALUES (1, 47, 'oe0Ej0QqaxWowCmmjXoE9McC7NsQ', '2018-01-19 08:55:06');
INSERT INTO `book_likerecord` VALUES (2, 48, 'oe0Ej0besxqth6muj72ZzfYGmMp0', '2018-01-24 20:36:20');
INSERT INTO `book_likerecord` VALUES (3, 48, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '2018-01-27 20:29:59');
INSERT INTO `book_likerecord` VALUES (4, 19, 'oe0Ej0eRfkPLRTDPZMXcUDGZcw9g', '2018-01-29 20:12:30');
INSERT INTO `book_likerecord` VALUES (5, 21, 'oe0Ej0eRfkPLRTDPZMXcUDGZcw9g', '2018-01-29 20:14:09');
INSERT INTO `book_likerecord` VALUES (6, 19, 'oe0Ej0YzlR1PwKXV78l8ZPQb19Ms', '2018-02-11 09:27:10');
INSERT INTO `book_likerecord` VALUES (7, 58, 'oe0Ej0WtcgZ6xEIdfwyKeY2keSHc', '2018-02-26 10:20:41');
INSERT INTO `book_likerecord` VALUES (8, 48, 'oe0Ej0WtcgZ6xEIdfwyKeY2keSHc', '2018-03-02 16:25:36');
INSERT INTO `book_likerecord` VALUES (9, 2, 'oe0Ej0QqaxWowCmmjXoE9McC7NsQ', '2018-03-05 14:47:14');
INSERT INTO `book_likerecord` VALUES (10, 48, 'oe0Ej0QqaxWowCmmjXoE9McC7NsQ', '2018-03-05 16:45:26');
INSERT INTO `book_likerecord` VALUES (11, 52, 'oe0Ej0QqaxWowCmmjXoE9McC7NsQ', '2018-03-05 17:17:48');
INSERT INTO `book_likerecord` VALUES (12, 48, 'oe0Ej0dsDIDIh-2l6ewqDIDmErOk', '2018-03-22 16:52:11');
INSERT INTO `book_likerecord` VALUES (13, 77, '0088', '2018-05-11 18:29:01');
INSERT INTO `book_likerecord` VALUES (14, 75, '0088', '2018-05-11 19:03:41');
INSERT INTO `book_likerecord` VALUES (15, 78, '0088', '2018-05-12 00:14:38');
INSERT INTO `book_likerecord` VALUES (16, 56, '0088', '2018-05-12 00:16:57');
INSERT INTO `book_likerecord` VALUES (17, 8, '3197', '2018-05-12 23:07:49');
INSERT INTO `book_likerecord` VALUES (18, 16, '3197', '2018-05-12 23:07:58');
INSERT INTO `book_likerecord` VALUES (19, 41, 'oe0Ej0XKpLLh8wn1UZDnWTGQ4NXM', '2018-05-13 17:10:58');
INSERT INTO `book_likerecord` VALUES (20, 76, 'oe0Ej0XKpLLh8wn1UZDnWTGQ4NXM', '2018-05-13 17:14:28');
INSERT INTO `book_likerecord` VALUES (21, 68, '3197', '2018-05-13 17:19:02');
INSERT INTO `book_likerecord` VALUES (22, 58, '0088', '2018-05-14 12:27:07');
INSERT INTO `book_likerecord` VALUES (23, 9, '0088', '2018-05-14 12:28:47');
INSERT INTO `book_likerecord` VALUES (24, 77, 'oe0Ej0besxqth6muj72ZzfYGmMp0', '2018-06-21 21:43:22');
INSERT INTO `book_likerecord` VALUES (25, 80, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '2018-07-06 21:08:07');
INSERT INTO `book_likerecord` VALUES (28, 78, 'oe0Ej0besxqth6muj72ZzfYGmMp0', '2018-07-16 12:49:07');
INSERT INTO `book_likerecord` VALUES (29, 80, 'oe0Ej0besxqth6muj72ZzfYGmMp0', '2018-07-18 10:55:52');

-- ----------------------------
-- Table structure for comment_likerecord
-- ----------------------------
DROP TABLE IF EXISTS `comment_likerecord`;
CREATE TABLE `comment_likerecord`  (
  `id` int(11) NOT NULL,
  `commentId` int(11) NOT NULL,
  `openid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `recTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `bookId` int(11) DEFAULT NULL,
  `headImgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_likerecord
-- ----------------------------
INSERT INTO `comment_likerecord` VALUES (1, 1, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '2018-07-25 16:42:56', 1, '12121212', '黄实');

-- ----------------------------
-- Table structure for cust_bindusers
-- ----------------------------
DROP TABLE IF EXISTS `cust_bindusers`;
CREATE TABLE `cust_bindusers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信小程序openid',
  `nickName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信昵称',
  `headImgUrl` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信头像Url',
  `userNo` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工号',
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `isAdmin` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '是否是管理员: 0：否；1：是。默认为0。',
  `recTime` datetime(0) NOT NULL COMMENT '绑定时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_userno`(`userNo`) USING BTREE,
  INDEX `idx_openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '员工绑定信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cust_bindusers
-- ----------------------------
INSERT INTO `cust_bindusers` VALUES (1, 'oe0Ej0besxqth6muj72ZzfYGmMp0', '阿黄同学😄', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHvy8Tuqkep87yztwR6SWkqY2JYBqVsaj7b4h7XH3ZCd7KWCZoLosLIA5C5uqmJpkhm55Y3R4mxg/0', '3693', '黄实', '0', '2018-01-17 23:27:05');
INSERT INTO `cust_bindusers` VALUES (2, 'oe0Ej0TwZrO1xOe3psfZ2NZYaxP0', '暖', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIKOpA3vN2AKnECajmTHY3NR8CNfDkSdlWrbDPp8xVBPowpgcEVHyRWPlR9QHvR5pQ7ZuM7O2xzvQ/0', '3533', '张文静', '0', '2018-01-18 10:03:59');
INSERT INTO `cust_bindusers` VALUES (3, 'oe0Ej0WIq4hde2usJ9DfpF5VttL8', '★★灬宝儿', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEJlibMIUUHxKoNibQYwc8v7lGrpf8iahSx4Lt7vZlbSufPeRlwexyR8bXxJWjVq4tDe1axpeOFw0hl9g/0', '3803', '钟源', '0', '2018-01-18 10:08:45');
INSERT INTO `cust_bindusers` VALUES (4, 'oe0Ej0d3pqW_Bi5Bmg2lvpSdOtRE', '常想一二的码农', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKaE3XoO100PjEvSQoeQvNiaibOI8KB17PFDK7n6pGyohCJV9KmbHP1BKedMyoH3m38IS3ibInID9eXw/0', '0167', '刁凤圣', '0', '2018-01-18 13:31:41');
INSERT INTO `cust_bindusers` VALUES (5, 'oe0Ej0Y6-o6pStdXABb0R0Ufi5hs', 'wanxin', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eojfdEyue7eav13vpUQcwQxRo059WnOThXhq5ly0fAAEqxZqxVbkLicu9lOiaoVnMLe3aqgvQlJicdFg/0', '3730', '徐万鑫', '0', '2018-01-18 14:10:39');
INSERT INTO `cust_bindusers` VALUES (6, 'oe0Ej0QpCtaTLoA24PzsbQzvdq3A', 'SIRIUS', 'https://wx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM4vmq8L2MEjrbTwxxBKRHXl06YA5dJTusRUBcWDgHpic42DtOyqXqUGfibPaXuLwO7ykdKWCa4nK4NA/0', '3421', '庞健', '0', '2018-01-18 15:12:28');
INSERT INTO `cust_bindusers` VALUES (7, 'oe0Ej0QqaxWowCmmjXoE9McC7NsQ', '亮亮', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEI3boegsA7xI2MrJppEuYgms9bgKWPqOBFQBqUrEokMhC4cAAovnv97YRPqqBx80KAnakPSgbjQTQ/0', '4840', '乔冬亮', '0', '2018-01-19 08:51:39');
INSERT INTO `cust_bindusers` VALUES (8, 'oe0Ej0QIzCEnLtALWYj_queZJv24', 'zyx', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK3JVibuZg8wiaDAmZMyc1IlDicaWHoHhuhW5vYxUmmslngyf2er8N4UEia3IicPicZFjfwH2td343lmYxA/0', '4555', '张英侠', '0', '2018-01-19 12:26:02');
INSERT INTO `cust_bindusers` VALUES (9, 'oe0Ej0Q-0npwv_4_3A-3EIAoIKUs', '红黑之吻', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIrWoh6dYCA8fN6eNyFedMXaL7cAN3FAnNUB0Zml1AJYEjPTe9zzibpokh2SHzUibznqDszSO5RicY6Q/0', '3261', '张威', '0', '2018-01-19 16:34:08');
INSERT INTO `cust_bindusers` VALUES (10, 'oe0Ej0aEg1BOwY33rCkQlArWzJr0', '李红梅', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKz3NN26nfe9Hs8w4CDQJZh8lc87226fCROCkwKY9y1s2UUeiaPQxP0PgfqIVWv5mWicPYnoMdgncaA/0', '3346', '李红梅', '0', '2018-01-21 10:45:25');
INSERT INTO `cust_bindusers` VALUES (11, 'oe0Ej0eRfkPLRTDPZMXcUDGZcw9g', 'summer', 'https://wx.qlogo.cn/mmopen/vi_32/cRC2CDbeMPJfMcuc2BwqR5385zK6m1JwGnm7uISdgYJgB4jFLmVK6gBNawLMeu917uAqGvMVibfacSvlKRATbMQ/0', '8888', '测试', '0', '2018-01-21 10:49:56');
INSERT INTO `cust_bindusers` VALUES (12, 'oe0Ej0S4VXro7zpEfRBGa7PlbnEU', 'miracle', 'https://wx.qlogo.cn/mmopen/vi_32/YTAibKUADIyp24YGyCT7MD5GtJaS5JmErzFXEBftWkpI7CTc6ZTn9MRCwNNcicnPDwnwaCtfltts5aZoHmia14wDw/0', '3798', '赵奇', '0', '2018-01-21 19:50:46');
INSERT INTO `cust_bindusers` VALUES (13, 'oe0Ej0Rw8g3uX6PP5aej8YFGs7ag', '左啸冰', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ6X2rz6oe3FPkExloCsTYAMl0ib7CliaQBonSkabrFgDNHMQ8RMraUAo0WRQQu9sfzCNsIU6eKwDow/0', '4349', '左啸冰', '0', '2018-01-22 08:57:04');
INSERT INTO `cust_bindusers` VALUES (14, 'oe0Ej0YIHCXdWZCnwqvSEL2GVpPc', '我是丫头', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJkTiahECI7SOLJ0eD6OWvACibNqthN5OkJzhBkbXvtO54htxkYgO73icm5S24FeIIg0kUlBqChSsG7w/0', '4412', '王婷婷', '0', '2018-01-22 09:45:02');
INSERT INTO `cust_bindusers` VALUES (15, 'oe0Ej0UlpbhyT7wX-viYhDuNr9BU', '天空', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqaQbzdM5ZiaFCAO4EgTX4YsalqzO7txN1mU1nq2HqCW9fP2qwwGyFBeb1v4T9EDrPm3bxKwuYLxLg/0', '3729', '郭晨', '0', '2018-01-22 09:46:24');
INSERT INTO `cust_bindusers` VALUES (16, 'oe0Ej0UuemF_vCpINd-vBKcz6_aM', '徐冉冉', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI5UerpZD07eZ46AobicNiajU5eqmSbmUr4sq0hvL1FMSVyzibrP8ph8kUT2cNHVcgibTLH6f8ZcIPylw/0', '4442', '徐冉冉', '0', '2018-01-22 09:47:16');
INSERT INTO `cust_bindusers` VALUES (17, 'oe0Ej0SDtcHGaKWW4rxlpfMzlxb0', '贺', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEJXUYRSvo5M5E1UL0Xs8EA7NmUuInbnpcGKhTYqa9cuich9cywQOHLBecOzlq8m0FYPmt8WRbXuDpw/0', '3552', '贺润东', '0', '2018-01-22 09:49:13');
INSERT INTO `cust_bindusers` VALUES (18, 'oe0Ej0Zs4ZyseKAz39Px3VtcS36g', '郭海的桥梁', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJnibBNBwJ2b4OhvEao46ruJD9CiaZmpxJTDf6PnL3AxlGiawyDGudic2KnS5aFRI0Z8xsJ1Yccc1wJyA/0', '0591', '郭海梁', '0', '2018-01-22 09:49:39');
INSERT INTO `cust_bindusers` VALUES (19, 'oe0Ej0UteVin1OcdVhIgBmm5vkn8', '张坤', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIpuibzKNmhug7H6pNztVoyYUxlk26fgVUTt6cQCmrq4eTTHyC2ggYxRZHOeiaSlDfLUPNicsfPgjT7Q/0', '3477', '张坤', '0', '2018-01-22 09:50:40');
INSERT INTO `cust_bindusers` VALUES (20, 'oe0Ej0WcoqfoppsewC0GqOLoZzso', '苗海柱', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJcsnVTAT3bO0PPAzia4qUQVic8xuyhvia4yQoIv8yam6ZY1MXemfv8icaaTcKfcYbpODfZSdIAAB3vpw/0', '4609', '苗海柱', '0', '2018-01-22 09:51:27');
INSERT INTO `cust_bindusers` VALUES (21, 'oe0Ej0UB9HItIcNzOBXt5J86dmho', '小乔', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLic9OoiavhjOcSu46YXhjYiasRBWiclm8MgyQy5BQUxYb6gx4wMoyjvowQQqRzzfcrn4L9LrwkDLLECg/0', '3268', '乔娟', '0', '2018-01-22 09:52:44');
INSERT INTO `cust_bindusers` VALUES (22, 'oe0Ej0benq09_-hUWpQh6fv4rzxE', 'ifelse', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqXTu1zLQSqFvQliaRpl872JUjAF6FVhdqibOniaaAYoe54FC5VRibHmT9ibSmZbziaficFxK05P7CHbJcGw/0', '4646', '陈恭泳', '0', '2018-01-22 09:53:38');
INSERT INTO `cust_bindusers` VALUES (23, 'oe0Ej0aOO5z_WtJ9ufzoiMx8RqcA', '四脚猫功夫', 'https://wx.qlogo.cn/mmopen/vi_32/JkAFQiaMtMJxAahBHjdHYlgOvfV0H8dnzCZk1TyMEEEYD6zhUz7LSSbg1r2K43atffomhKbRe4Kt3vTCnVhiaH5Q/0', '4105', '王玉婷', '0', '2018-01-22 09:54:52');
INSERT INTO `cust_bindusers` VALUES (24, 'oe0Ej0WDAe0Y2UFn1_jz8GEB2MFU', '高高', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLG2ye0Lic3ZDS7OHFHSQ4RwhhZPl0iapZ2C3lzCYj4sG2icQ4IEWKPMf6ZK9OU5VJ4cglq5IFkj9YYQ/0', '4079', '高雅', '0', '2018-01-22 09:59:43');
INSERT INTO `cust_bindusers` VALUES (25, 'oe0Ej0UAzEbQ6PmkW1J3EW8X8D-Q', '雪梅', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK15XQkJ1qP8OQoickQjBtj2AyKS2Yj3GB5Y3ZRuibEibeymJewFQ7HjrXbdyfr7ibTVuqiaRiaUuLv9vUw/0', '3750', '杨雪梅', '0', '2018-01-22 10:03:56');
INSERT INTO `cust_bindusers` VALUES (26, 'oe0Ej0Q3v0JtZkcFlwz6cu6jOSeQ', 'scq', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL7pexxicTgIdaNC77iakPRibdfu8U0qncOlbkCxP6cHOD3ABwKukzjygg8ntic19cIOncxf43Rc7w3Rg/0', '3114', '宋承谦', '0', '2018-01-22 10:10:10');
INSERT INTO `cust_bindusers` VALUES (27, 'oe0Ej0dvIHPx1NHZxJ6jKCoHNsdg', '静茵 覆', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eokHNcRJTzwJB7CiaWvUJkdnZSTCmROe4rFicwm6icOE2EQvzU2rZk2cE8JzQzAyQUsZKeAg7icQvULUg/0', '4072', '付皓婉', '0', '2018-01-22 10:32:54');
INSERT INTO `cust_bindusers` VALUES (28, 'oe0Ej0ZxMqIq_AfyookjxmmTz5mI', 'teddyBear', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIchS8HM7xZsq5e9CIXacGJMBc8hpqMfDo91XoY1Tqod9ZCib3NNxJdq7mbKc8VUNO6XdkoViaQfHKg/0', '3718', '熊睿', '0', '2018-01-22 11:06:23');
INSERT INTO `cust_bindusers` VALUES (29, 'oe0Ej0fIBmXSILiRSCiLy8beodlo', '三原色', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eouJKnmV4ORwM0Odgdic6hqc7fJU4twJym2RLOLLnTw0EGybiaOeDT4s1zwQgadpDWPoFHIbFia5yicWg/0', '3334', '杨达', '0', '2018-01-22 11:17:05');
INSERT INTO `cust_bindusers` VALUES (30, 'oe0Ej0Vthq8ITJfLGHkaU0nJcp08', '烁烁', 'https://wx.qlogo.cn/mmopen/vi_32/e7IR25Ur1mQhCiaLWtmOJ5PhaYUukhicZU9Fnks6FG5V3zAln8QtlPWKhVslNLcFDQgp3acwrE156L36EdkvWJhQ/0', '4488', '李烁', '0', '2018-01-22 11:21:15');
INSERT INTO `cust_bindusers` VALUES (31, 'oe0Ej0ZnCa2fL3swg1zDgaLgVCUI', '阿列夫零', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLDqK1KGoc61icKeRN5bU21vBqP7azoTibU2Cia8ACgWTLs1volnJoLG5p8o9SGicLMpSgFjIXiacxD2qg/0', '4415', '李延彬', '0', '2018-01-22 11:29:34');
INSERT INTO `cust_bindusers` VALUES (32, 'oe0Ej0RL3I7o4R8LJSnivZmKVTIY', '果仁儿', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKWBIdOvJs5fhvOK5ATcE4Kiae5kevygjTx7AFicUkpoRnFPywyn4uyXr4g1ybw8xaYTkJpTyH4WRmA/0', '3428', '郭有光', '0', '2018-01-22 11:48:49');
INSERT INTO `cust_bindusers` VALUES (33, 'oe0Ej0Z5nLgZJUl1z1fDB7TUttkU', '朱莹', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJCzAG0lh2uzJ1z1rVayDtfQhaicWTXmNMeARiaKkZob7812icXQaEehZyrMSM908suOZPuR6fEqg2PA/0', '0631', '朱莹', '0', '2018-01-22 13:02:29');
INSERT INTO `cust_bindusers` VALUES (34, 'oe0Ej0VPEej4t7dhmI6aN_3Y9bXQ', '吴雪', 'https://wx.qlogo.cn/mmopen/vi_32/nTqfgtV0vrTjCzVWvLwB56UxfRmZaLb2je7N8P27SaQqjlD360f4hd1mNvUwV6Lke5Z6HOYh9I5qu8h2ickMr4A/0', '4434', '吴雪', '0', '2018-01-22 13:39:49');
INSERT INTO `cust_bindusers` VALUES (35, 'oe0Ej0ZLrFRIw1miwL1B2f8G6UDE', '叫我马良', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIyvtiaJgtGBmgghcXtDTEVdic0vgl9rCQwib4F9N3V4FQVIIMBZMYUXxaib6z6PKKjayoowaKNzU9yTg/0', '4198', '马良', '0', '2018-01-22 17:25:25');
INSERT INTO `cust_bindusers` VALUES (36, 'oe0Ej0QDKVSRHFXZH3ZiDYt_wRAU', 'chenchen', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIdmxUuslFdV9CcP4xLkcJQalGu8aGvokSJG9D63J9FcW2iccNx6fdAM58KZn4rI8JRfgP2ic3tjXuw/0', '3692', '李冰', '0', '2018-01-22 18:31:47');
INSERT INTO `cust_bindusers` VALUES (37, 'oe0Ej0Tf74_JPnYATB2csgwP5Gt8', 'LEE', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epG45LjZ7le38nTOvYL5FGXWQfCzs1HkAibc2xV4OwZG9S5PAvS37ibEIXY8G0UgyuCqSE3vicE52R2g/0', '3541', '李炜', '0', '2018-01-23 08:54:07');
INSERT INTO `cust_bindusers` VALUES (38, 'oe0Ej0cAyGClWxAZDgA8SuZmLf6s', '陈宥余', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLWFraWKQSic9Ebe2Jiasrdaib4cDOacLBx3okxNsRRTlgiclQj78vgXibic4TRSLrgkk77cqEZmc7x62sg/0', '3507', '陈宥余', '0', '2018-01-23 10:41:11');
INSERT INTO `cust_bindusers` VALUES (39, 'oe0Ej0Ww5QeLrnZpcPwzI4NBx5H8', 'GE', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK4SHibPyO2LZicNpNDicicvlgwk4BMPeSvl93Bm21L8AZruZzGPg29zQL0nM2PUiaa6KRClic4nR3h4UTA/0', '3564', '葛翔宇', '0', '2018-01-23 11:06:42');
INSERT INTO `cust_bindusers` VALUES (40, 'oe0Ej0a2DBxx0fJiEeI9SE7T73kM', '雪儿', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJfB9GRLtYlNzVaBBEDMSVpFb0loWEAvJd2IMPibFSRtXkwoicAeFnJ1IL9K4VrCalw4ly8wReHp5Mw/0', '3429', '韩雪', '0', '2018-01-23 17:15:45');
INSERT INTO `cust_bindusers` VALUES (41, 'oe0Ej0SfqUZHv8Uv526KGavXhVQk', '谭海之光', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJUaOs097vUdwicmIDEs2AhbJAcjSPIvxKyd1cxFmXQjykKnD0Zk5ibhq9ywdmls5WyRuZRmb6V5W3g/0', '4054', '马丽娇', '0', '2018-01-24 11:15:20');
INSERT INTO `cust_bindusers` VALUES (42, 'oe0Ej0Z2cE_-gMha3SYayk2pOqHA', '木易', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKAN9WI6xiacTWT8VFicZBibPcNo5hibfWxr3mSPW7I0vp2NF2z2wh8ulIKqzX71Ft9cHibXbf7icMuknFA/0', '4616', '杨帆4616', '0', '2018-01-24 18:25:58');
INSERT INTO `cust_bindusers` VALUES (43, 'oe0Ej0VQe9X567RfyucvGD55fg8I', '许笠晨', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEQ6FkGMjPp8hG0yFErf6ib27hKr0KARMETfzPL7Do6TLhQoJokibY6OjoQav1WM81WMsRTsoILckQ/0', '0932', '许笠晨', '0', '2018-01-25 07:49:00');
INSERT INTO `cust_bindusers` VALUES (44, 'oe0Ej0afgRoJ9QqCAeJuu-a9tYMo', '贾华占', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLNCf0hFgqd9Piaibnkjceiclpyw32pc8ib6UdPGa6Xv5tE2wHnwmkxz5hR88ZZHic6m7y0npmSXnbMRlQ/0', '4611', '贾花占', '0', '2018-01-25 08:59:30');
INSERT INTO `cust_bindusers` VALUES (45, 'oe0Ej0YzlR1PwKXV78l8ZPQb19Ms', '米坤明', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIYNs91AuVo6Pxia6J25kxuXNibbaDM79Tkyek7R8WW57vefq0pdrsQgvX5UCMAZMJfOFqeF4XY4jOg/0', '0482', '米坤明', '0', '2018-01-25 11:48:14');
INSERT INTO `cust_bindusers` VALUES (46, 'oe0Ej0U29c72xT-GszpdZJWdftRo', '天使尘', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJqKyQZd7r9tl3mP9wzzsoxkA0exxomuZLEwUNSFT324K8TWcPlwxb6riaZiakviaZ1QVibqXMMnsicYSg/0', '4212', '齐昕', '0', '2018-02-15 23:22:32');
INSERT INTO `cust_bindusers` VALUES (47, 'oe0Ej0bGAySbIbkfoqpzCJXHH2DI', 'liwei  xie。', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK9QgMJF3tzxQaTm3JnicoqXjCVNB81z9T05Ygb1hCM5Nlq4DebcSzwiav7icEvADVqNJAtoApo92MSA/0', '4643', '颉李伟', '0', '2018-02-24 16:01:40');
INSERT INTO `cust_bindusers` VALUES (48, 'oe0Ej0WtcgZ6xEIdfwyKeY2keSHc', 'Lin', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ephyibWW63v5wZJ9icCibCLI52xXcZBVSVOSkykaf06GCLI1ibWZPRFToO3hyQlACAXNaWPYOWUtXCQXg/0', '4811', '杨蕾', '0', '2018-02-24 17:21:41');
INSERT INTO `cust_bindusers` VALUES (49, 'oe0Ej0WmQLORuDMQz0UzZnZAjV34', '南瓜nn', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIwY1mhUqD8PibkAkiaVsB5pByfxzZ3nHJ2xkEzl9ib39dzcH7qaxuOV8rvFic5AsVsOplPuFarecch4A/0', '4216', '贾楠', '0', '2018-02-26 12:27:05');
INSERT INTO `cust_bindusers` VALUES (50, 'oe0Ej0S9Vsp0D6a4XNEnOnyKuOVU', 'bluewolf', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLh8ubWQtDRaibg6wgEs6qw2fJDDK66g3eSCPrb4C4HLibww0yYZgPkIUI9bnBLjtVGQW1icbWZWPwhg/0', '0372', '李朋', '0', '2018-02-26 14:10:32');
INSERT INTO `cust_bindusers` VALUES (51, 'oe0Ej0eLxayk9IyeDOrhF4zzJJaE', '斯', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKYiav7X4pnODvwgPTniaJ69hsiaKJt3fkTkhuooF285t0T4DcTWlpIic6icpOZryyJINsuWDicVqk8hatA/0', '3536', '张雅斯', '0', '2018-02-26 16:02:59');
INSERT INTO `cust_bindusers` VALUES (52, 'oe0Ej0RNFxxm692DTryk_wCh614s', 'Crystalbabe', 'https://wx.qlogo.cn/mmopen/vi_32/99wjWiboakMEaBFo1yPnAl1Licq44ib3DCibpgp1FzzAYOa68f6RbXdALz7PgRsIlhS2c9LR7N85BCLgZ3LmepZGcA/0', '4776', '霍雨佳', '0', '2018-02-27 09:29:04');
INSERT INTO `cust_bindusers` VALUES (53, 'oe0Ej0dsDIDIh-2l6ewqDIDmErOk', '坦克', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqBYD3lFhbm55JvCsSicUpNFB3FdIfX0ccA954eGuk1icaqrrMGsdiaSGxkD7ibYvV4vZ1KeeyXO9R1Sw/0', '3525', '王孟亭', '0', '2018-03-22 16:51:21');
INSERT INTO `cust_bindusers` VALUES (54, 'oe0Ej0X0uNN4xvSvTNuO_dmae1DY', 'Fxy', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ereGMxKU6ODibZV8D5xvKB7ibuYJo1Qm97h1X6EO3rd4M7md55NPs4cmoSfZmeicOMobeRlyvFhoI82Q/0', '4639', '傅湘宜', '0', '2018-04-10 20:56:43');
INSERT INTO `cust_bindusers` VALUES (55, 'oe0Ej0XKpLLh8wn1UZDnWTGQ4NXM', '海边等鲨鱼', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEL98AlpNoTbUrzkDlK7vEjtBee7KbpjpYEJJGqOVI0d7zuD7aDcOBKt5DDoYeicsoegufjaGyUAcRA/0', '3197', '高勇', '0', '2018-04-12 09:57:37');
INSERT INTO `cust_bindusers` VALUES (56, 'oe0Ej0XFS-8D6QfDyKztiR6ed5Oc', '叶小谋', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK5CMGuzmdjG8eFD5vK39IUsqG2n3DqxQsqQp1D5ibiaSLQ5kAxeh4TiaibVkjMh7OYwYibuW1iafucDdug/0', '7112', '叶小谋', '0', '2018-04-12 15:02:47');
INSERT INTO `cust_bindusers` VALUES (57, 'oe0Ej0VqfU2XwOGNMu2CkItWCONc', '齐子儿C', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL4xp80TjjnbYAb4mHEewG2l5J2GpznaibafZDZ2qHj0PicgDPErIOBXbCL6JEeYeiarNPV3RGEWCVVg/0', '4065', '许超', '0', '2018-04-13 14:33:01');
INSERT INTO `cust_bindusers` VALUES (58, 'oe0Ej0ZTKpsjfM2AnYjEHbhLwqHM', 'Hendery', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqwVv13EV6BMcGEbWAibyViaEvJ0jwVUnbyNVbibuAaWk6c59RWbknPSgAjhG3icib127sEiaSsPMQxPjTg/132', '3835', '韩春阳', '0', '2018-07-03 14:47:46');
INSERT INTO `cust_bindusers` VALUES (59, 'oe0Ej0c8zOzx1lEPEwOSCexRkr6c', '北极雪', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEL7QqNzbLYpafib1Z4MEvRN7mYkl58fELKcpzb9XZ46jK9bQtJQag8IQAwdKp5HBOowBk9AOE6XibBw/132', '3478', '张旭', '0', '2018-07-03 14:56:37');
INSERT INTO `cust_bindusers` VALUES (60, 'oe0Ej0c7rdtZ4ZMCTMHYTUD0divQ', '咚塔塔', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKFRiaB7ribq1a6lZmz5SKPFCibnVM9DJWkgDeHJlhPUZdUx13OBibGciagIjeNAA9dfEXW0VRkr41VAibw/132', '3787', '陶思嘉', '0', '2018-07-03 14:56:52');
INSERT INTO `cust_bindusers` VALUES (61, 'oe0Ej0TqIkG0TbAMxrbo886hjULU', 'caoqian', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ercLxtM6Fic9YEVwLYiciar7MrmSlMq0h7qnHziaUHGPv2cJ4POXIMFTNgS4nkDA2iaPMd9DsBaIQdpuSQ/132', '3837', '曹茜', '0', '2018-07-06 17:22:05');
INSERT INTO `cust_bindusers` VALUES (62, 'oe0Ej0Vo9XYoN93z5ACOi7Y-xWU0', 'ribbonstar', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJV2PU4QW5xMQBJrk0QrnF8rWRssT5mIGDTMf2AtIaQgWicXTokzYtFHjibb7oDwlF0uazXhKtn2MvQ/132', '0529', '李渤', '0', '2018-07-10 10:59:42');
INSERT INTO `cust_bindusers` VALUES (63, 'oe0Ej0eJ2RgLjcU_4RnojSwDSH9I', '靳君', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ermUt4Jo90WJS9vicfNBMCrwRf0LGpjzkdENfsSc9n4bKzadg2v0Xm8S5OLEUft8t0jepIrER9d7iaQ/132', '3095', '靳君', '0', '2018-07-13 12:34:57');
INSERT INTO `cust_bindusers` VALUES (64, 'oe0Ej0VbUk3RqwD-Wo9jOK0Fznz4', 'EricVan', 'https://wx.qlogo.cn/mmopen/vi_32/Ht0Y1Ue37aPaVicn29Jcdiae5hzMQTpbibldZzypr7s4GBvibywvcrj2vyKkkIAhHZibWS5dkyibDpicnY5DsxU0SEdxQ/132', '3817', '冯欢', '0', '2018-07-13 12:36:26');

-- ----------------------------
-- Table structure for cust_users
-- ----------------------------
DROP TABLE IF EXISTS `cust_users`;
CREATE TABLE `cust_users`  (
  `userNo` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工号',
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工姓名',
  `userEmail` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '员工邮箱',
  PRIMARY KEY (`userNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '员工基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cust_users
-- ----------------------------
INSERT INTO `cust_users` VALUES ('0088', '姜燕', '');
INSERT INTO `cust_users` VALUES ('0156', '曹克', '');
INSERT INTO `cust_users` VALUES ('0167', '刁凤圣', '');
INSERT INTO `cust_users` VALUES ('0182', '郭思宇', '');
INSERT INTO `cust_users` VALUES ('0190', '黄静', '');
INSERT INTO `cust_users` VALUES ('0264', '王旭红', '');
INSERT INTO `cust_users` VALUES ('0284', '张帆', '');
INSERT INTO `cust_users` VALUES ('0323', '马骊', '');
INSERT INTO `cust_users` VALUES ('0358', '方曦', '');
INSERT INTO `cust_users` VALUES ('0372', '李朋', '');
INSERT INTO `cust_users` VALUES ('0384', '张巍', '');
INSERT INTO `cust_users` VALUES ('0399', '林雪南', '');
INSERT INTO `cust_users` VALUES ('0482', '米坤明', '');
INSERT INTO `cust_users` VALUES ('0526', '黄新福', '');
INSERT INTO `cust_users` VALUES ('0529', '李渤', '');
INSERT INTO `cust_users` VALUES ('0575', '马红', '');
INSERT INTO `cust_users` VALUES ('0590', '黄河峰', '');
INSERT INTO `cust_users` VALUES ('0591', '郭海梁', '');
INSERT INTO `cust_users` VALUES ('0606', '郭雪莎', '');
INSERT INTO `cust_users` VALUES ('0609', '贾凌娜', '');
INSERT INTO `cust_users` VALUES ('0631', '朱莹', '');
INSERT INTO `cust_users` VALUES ('0662', '钱峰', '');
INSERT INTO `cust_users` VALUES ('0712', '刘洋', '');
INSERT INTO `cust_users` VALUES ('0768', '陈水清', '');
INSERT INTO `cust_users` VALUES ('0859', '邱岩', '');
INSERT INTO `cust_users` VALUES ('0862', '焦培', '');
INSERT INTO `cust_users` VALUES ('0913', '郄春蕊', '');
INSERT INTO `cust_users` VALUES ('0932', '许笠晨', '');
INSERT INTO `cust_users` VALUES ('0934', '汤跃林', '');
INSERT INTO `cust_users` VALUES ('0952', '王同庆', '');
INSERT INTO `cust_users` VALUES ('0985', '武岳', '');
INSERT INTO `cust_users` VALUES ('2240', '崔煜堃', '');
INSERT INTO `cust_users` VALUES ('3016', '洪晓峰', '');
INSERT INTO `cust_users` VALUES ('3033', '徐超', '');
INSERT INTO `cust_users` VALUES ('3095', '靳君', '');
INSERT INTO `cust_users` VALUES ('3106', '肖遥', '');
INSERT INTO `cust_users` VALUES ('3114', '宋承谦', '');
INSERT INTO `cust_users` VALUES ('3116', '张子辉', '');
INSERT INTO `cust_users` VALUES ('3167', '蔡伶', '');
INSERT INTO `cust_users` VALUES ('3191', '孙景景', '');
INSERT INTO `cust_users` VALUES ('3197', '高勇', '');
INSERT INTO `cust_users` VALUES ('3217', '张温文', '');
INSERT INTO `cust_users` VALUES ('3248', '李佳', '');
INSERT INTO `cust_users` VALUES ('3260', '崔晓军', '');
INSERT INTO `cust_users` VALUES ('3261', '张威', '');
INSERT INTO `cust_users` VALUES ('3268', '乔娟', '');
INSERT INTO `cust_users` VALUES ('3273', '李涛', '');
INSERT INTO `cust_users` VALUES ('3274', '张兴华', '');
INSERT INTO `cust_users` VALUES ('3275', '谷方方', '');
INSERT INTO `cust_users` VALUES ('3284', '张铁成', '');
INSERT INTO `cust_users` VALUES ('3315', '钟萃芳', '');
INSERT INTO `cust_users` VALUES ('3334', '杨达', '');
INSERT INTO `cust_users` VALUES ('3335', '顾妍', '');
INSERT INTO `cust_users` VALUES ('3345', '王倩', '');
INSERT INTO `cust_users` VALUES ('3346', '李红梅', '');
INSERT INTO `cust_users` VALUES ('3348', '李小艳', '');
INSERT INTO `cust_users` VALUES ('3350', '曾玉冰', '');
INSERT INTO `cust_users` VALUES ('3375', '王鹏晴', '');
INSERT INTO `cust_users` VALUES ('3397', '梁蓉蓉', '');
INSERT INTO `cust_users` VALUES ('3419', '陈新雨', '');
INSERT INTO `cust_users` VALUES ('3421', '庞健', '');
INSERT INTO `cust_users` VALUES ('3425', '魏薇', '');
INSERT INTO `cust_users` VALUES ('3428', '郭有光', '');
INSERT INTO `cust_users` VALUES ('3429', '韩雪', '');
INSERT INTO `cust_users` VALUES ('3437', '裴艳霞', '');
INSERT INTO `cust_users` VALUES ('3443', '邓晓彬', '');
INSERT INTO `cust_users` VALUES ('3444', '陈稳', '');
INSERT INTO `cust_users` VALUES ('3445', '王秋利', '');
INSERT INTO `cust_users` VALUES ('3446', '毕珊', '');
INSERT INTO `cust_users` VALUES ('3449', '杨琳', '');
INSERT INTO `cust_users` VALUES ('3459', '朱正新', '');
INSERT INTO `cust_users` VALUES ('3462', '张大勇', '');
INSERT INTO `cust_users` VALUES ('3465', '张翼飞', '');
INSERT INTO `cust_users` VALUES ('3477', '张坤', '');
INSERT INTO `cust_users` VALUES ('3478', '张旭', '');
INSERT INTO `cust_users` VALUES ('3495', '王硕', '');
INSERT INTO `cust_users` VALUES ('3502', '孙少雄', '');
INSERT INTO `cust_users` VALUES ('3504', '赵燕', '');
INSERT INTO `cust_users` VALUES ('3507', '陈宥余', '');
INSERT INTO `cust_users` VALUES ('3514', '王贺超', '');
INSERT INTO `cust_users` VALUES ('3520', '杨毅刚', '');
INSERT INTO `cust_users` VALUES ('3525', '王孟亭', '');
INSERT INTO `cust_users` VALUES ('3533', '张文静', '');
INSERT INTO `cust_users` VALUES ('3536', '张雅斯', '');
INSERT INTO `cust_users` VALUES ('3537', '王颖', '');
INSERT INTO `cust_users` VALUES ('3540', '张澳雪', '');
INSERT INTO `cust_users` VALUES ('3541', '李炜', '');
INSERT INTO `cust_users` VALUES ('3542', '李思远', '');
INSERT INTO `cust_users` VALUES ('3544', '杨东', '');
INSERT INTO `cust_users` VALUES ('3545', '韩桂子', '');
INSERT INTO `cust_users` VALUES ('3547', '胡鹏', '');
INSERT INTO `cust_users` VALUES ('3552', '贺润东', '');
INSERT INTO `cust_users` VALUES ('3554', '杨荣华', '');
INSERT INTO `cust_users` VALUES ('3555', '李春霞', '');
INSERT INTO `cust_users` VALUES ('3556', '刘星', '');
INSERT INTO `cust_users` VALUES ('3558', '张钊', '');
INSERT INTO `cust_users` VALUES ('3564', '葛翔宇', '');
INSERT INTO `cust_users` VALUES ('3565', '逯美玲', '');
INSERT INTO `cust_users` VALUES ('3570', '孔维晨', '');
INSERT INTO `cust_users` VALUES ('3582', '张丽娟', '');
INSERT INTO `cust_users` VALUES ('3595', '郑兴艳', '');
INSERT INTO `cust_users` VALUES ('3602', '刘一梦', '');
INSERT INTO `cust_users` VALUES ('3606', '高鹏', '');
INSERT INTO `cust_users` VALUES ('3612', '刘红艳', '');
INSERT INTO `cust_users` VALUES ('3613', '杨扬', '');
INSERT INTO `cust_users` VALUES ('3667', '邓燕宁', '');
INSERT INTO `cust_users` VALUES ('3681', '王俐之', '');
INSERT INTO `cust_users` VALUES ('3683', '王园', '');
INSERT INTO `cust_users` VALUES ('3685', '梁田', '');
INSERT INTO `cust_users` VALUES ('3691', '齐海智', '');
INSERT INTO `cust_users` VALUES ('3692', '李冰', '');
INSERT INTO `cust_users` VALUES ('3693', '黄实', '');
INSERT INTO `cust_users` VALUES ('3700', '宋爽', '');
INSERT INTO `cust_users` VALUES ('3710', '任婷', '');
INSERT INTO `cust_users` VALUES ('3711', '王铖', '');
INSERT INTO `cust_users` VALUES ('3712', '彭雅华', '');
INSERT INTO `cust_users` VALUES ('3715', '梁宏雨', '');
INSERT INTO `cust_users` VALUES ('3718', '熊睿', '');
INSERT INTO `cust_users` VALUES ('3721', '朱琳', '');
INSERT INTO `cust_users` VALUES ('3724', '卜梦醒', '');
INSERT INTO `cust_users` VALUES ('3729', '郭晨', '');
INSERT INTO `cust_users` VALUES ('3730', '徐万鑫', '');
INSERT INTO `cust_users` VALUES ('3737', '马秀红', '');
INSERT INTO `cust_users` VALUES ('3740', '何荣添', '');
INSERT INTO `cust_users` VALUES ('3743', '李国银', '');
INSERT INTO `cust_users` VALUES ('3750', '杨雪梅', '');
INSERT INTO `cust_users` VALUES ('3751', '薛文莲', '');
INSERT INTO `cust_users` VALUES ('3757', '王文清', '');
INSERT INTO `cust_users` VALUES ('3781', '孙娟', '');
INSERT INTO `cust_users` VALUES ('3787', '陶思嘉', '');
INSERT INTO `cust_users` VALUES ('3789', '翟海廷', '');
INSERT INTO `cust_users` VALUES ('3798', '赵奇', '');
INSERT INTO `cust_users` VALUES ('3803', '钟源', '');
INSERT INTO `cust_users` VALUES ('3804', '曹佳硕', '');
INSERT INTO `cust_users` VALUES ('3817', '冯欢', '');
INSERT INTO `cust_users` VALUES ('3835', '韩春阳', '');
INSERT INTO `cust_users` VALUES ('3837', '曹茜', '');
INSERT INTO `cust_users` VALUES ('3845', '迟闯', '');
INSERT INTO `cust_users` VALUES ('3853', '赵晨露', '');
INSERT INTO `cust_users` VALUES ('4034', '陈思佳', '');
INSERT INTO `cust_users` VALUES ('4054', '马丽娇', '');
INSERT INTO `cust_users` VALUES ('4065', '许超', '');
INSERT INTO `cust_users` VALUES ('4068', '朱明会', '');
INSERT INTO `cust_users` VALUES ('4072', '付皓婉', '');
INSERT INTO `cust_users` VALUES ('4074', '刘舒', '');
INSERT INTO `cust_users` VALUES ('4075', '沙倩', '');
INSERT INTO `cust_users` VALUES ('4077', '许敏', '');
INSERT INTO `cust_users` VALUES ('4079', '高雅', '');
INSERT INTO `cust_users` VALUES ('4080', '曹淑娟', '');
INSERT INTO `cust_users` VALUES ('4081', '边威', '');
INSERT INTO `cust_users` VALUES ('4105', '王玉婷', '');
INSERT INTO `cust_users` VALUES ('4116', '韩骅宇', '');
INSERT INTO `cust_users` VALUES ('4147', '代威', '');
INSERT INTO `cust_users` VALUES ('4171', '王亚非', '');
INSERT INTO `cust_users` VALUES ('4196', '张晶', '');
INSERT INTO `cust_users` VALUES ('4197', '刘建松', '');
INSERT INTO `cust_users` VALUES ('4198', '马良', '');
INSERT INTO `cust_users` VALUES ('4212', '齐昕', '');
INSERT INTO `cust_users` VALUES ('4216', '贾楠', '');
INSERT INTO `cust_users` VALUES ('4217', '姚书会', '');
INSERT INTO `cust_users` VALUES ('4278', '余飞', '');
INSERT INTO `cust_users` VALUES ('4301', '陈思', '');
INSERT INTO `cust_users` VALUES ('4349', '左啸冰', '');
INSERT INTO `cust_users` VALUES ('4395', '赵欢', '');
INSERT INTO `cust_users` VALUES ('4412', '王婷婷', '');
INSERT INTO `cust_users` VALUES ('4415', '李延彬', '');
INSERT INTO `cust_users` VALUES ('4421', '吴春静', '');
INSERT INTO `cust_users` VALUES ('4422', '封寰新', '');
INSERT INTO `cust_users` VALUES ('4423', '孙伟', '');
INSERT INTO `cust_users` VALUES ('4424', '牛国庆', '');
INSERT INTO `cust_users` VALUES ('4427', '齐云凤', '');
INSERT INTO `cust_users` VALUES ('4428', '李兆阳', '');
INSERT INTO `cust_users` VALUES ('4429', '樊聪', '');
INSERT INTO `cust_users` VALUES ('4431', '吴亚东', '');
INSERT INTO `cust_users` VALUES ('4434', '吴雪', '');
INSERT INTO `cust_users` VALUES ('4438', '孔繁旭', '');
INSERT INTO `cust_users` VALUES ('4441', '魏兴', '');
INSERT INTO `cust_users` VALUES ('4442', '徐冉冉', '');
INSERT INTO `cust_users` VALUES ('4479', '康鑫', '');
INSERT INTO `cust_users` VALUES ('4488', '李烁', '');
INSERT INTO `cust_users` VALUES ('4545', '吴少红', '');
INSERT INTO `cust_users` VALUES ('4555', '张英侠', '');
INSERT INTO `cust_users` VALUES ('4560', '李显宽', '');
INSERT INTO `cust_users` VALUES ('4582', '赵亮', '');
INSERT INTO `cust_users` VALUES ('4583', '王大路', '');
INSERT INTO `cust_users` VALUES ('4584', '刘锐', '');
INSERT INTO `cust_users` VALUES ('4609', '苗海柱', '');
INSERT INTO `cust_users` VALUES ('4611', '贾花占', '');
INSERT INTO `cust_users` VALUES ('4616', '杨帆4616', '');
INSERT INTO `cust_users` VALUES ('4637', '王帅', '');
INSERT INTO `cust_users` VALUES ('4639', '傅湘宜', '');
INSERT INTO `cust_users` VALUES ('4643', '颉李伟', '');
INSERT INTO `cust_users` VALUES ('4646', '陈恭泳', '');
INSERT INTO `cust_users` VALUES ('4754', '智益', '');
INSERT INTO `cust_users` VALUES ('4767', '高艳红', '');
INSERT INTO `cust_users` VALUES ('4771', '郭赫', '');
INSERT INTO `cust_users` VALUES ('4776', '霍雨佳', '');
INSERT INTO `cust_users` VALUES ('4785', '刘文超', '');
INSERT INTO `cust_users` VALUES ('4811', '杨蕾', '');
INSERT INTO `cust_users` VALUES ('4814', '张博文', '');
INSERT INTO `cust_users` VALUES ('4840', '乔冬亮', '');
INSERT INTO `cust_users` VALUES ('4887', '李畅通', '');
INSERT INTO `cust_users` VALUES ('4896', '刘倩', '');
INSERT INTO `cust_users` VALUES ('4902', '朴雪威', '');
INSERT INTO `cust_users` VALUES ('7088', '郑珂', '');
INSERT INTO `cust_users` VALUES ('7109', '吴书迪', '');
INSERT INTO `cust_users` VALUES ('7112', '叶小谋', '');
INSERT INTO `cust_users` VALUES ('7158', '李驰', '');
INSERT INTO `cust_users` VALUES ('7181', '武有文', '');
INSERT INTO `cust_users` VALUES ('7211', '胡小磊', '');
INSERT INTO `cust_users` VALUES ('8888', '测试', '');

-- ----------------------------
-- Table structure for message_baseinfo
-- ----------------------------
DROP TABLE IF EXISTS `message_baseinfo`;
CREATE TABLE `message_baseinfo`  (
  `id` int(11) NOT NULL,
  `operationType` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operationContent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operationTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bookId` int(11) NOT NULL,
  `bookName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_baseinfo
-- ----------------------------
INSERT INTO `message_baseinfo` VALUES (1, '1', '1', '2018-07-25 11:38:23', '黄实', 1, '中国震撼');

SET FOREIGN_KEY_CHECKS = 1;
