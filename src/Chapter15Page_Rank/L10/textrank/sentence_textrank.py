# -*- coding: utf-8 -*-
import jieba
import jieba.analyse
import jieba.posseg as pseg

#sentence = '7月23日，在万般期待中，各地2020年高考录取分数线陆续公布，拿到分数后的考生们可谓有人欢喜有人愁。安徽省当天也公布了录取分数线，其中文理科一本分数线分别为541分和515分，二本分数线分别为499分和435分。那么被称为亚洲最大的“高考工厂”毛坦厂中学高考成绩怎样？图为毛坦厂中学教室午休的考生。7月23日，毛坦厂中学所在的安徽六安市毛坦厂镇官方发布喜报称，根据网络查询，六安市毛坦厂中学、金安高级中学、金安中学补习班2020年高考再创佳绩，再传捷报，安徽籍考生（不含外省考生）本科达线人数10176人，其中600分以上600分以上290人，理科最高分674分，文科最高分608分。也就是在2020年高考中，高考镇有1万多人达到本科线，听起来多少有点让人吃惊，这个数字还不包括其他十几个省份在毛坦厂复读的数百名考生。图为高考镇放学盛大的场面。今年的高考很特殊，因为疫情课业停摆两个多月，随后高考推迟一个月才考试，而就在考试进行时，安徽歙县因为洪水语文和数学被延期考试，湖北黄梅的考生硬是蹚水去考场，而云南一地的考生，考试时还发生了地震。图为高考前，高考镇的卡点。毛坦厂镇因为高考而驰名，被称为“亚洲最大的高考工厂”，外媒曾称该镇是一座偏僻的单一产业城镇，出产的是应试机器，就像其他一些专门生产袜子或圣诞饰品的中国乡镇一样心无旁骛。毛坦厂镇今年参加高考的考生人数超过1.7万人，涉及全国十多个省份。图为考生在上晚自习。据悉，毛坦厂镇面积只有3.5平方公里，人口不过万余人，从1999年后，毛坦厂中学借高考复读声名鹊起，每年都要吸引2万多学生和1万多陪读家长，整个小镇接近5万人。租房、生活、学习，众多学生和家长的到来，小镇的经济都围绕着“高考”在转。图为每天早晨，陪读家长在洗衣。在高考前，为了保障高考的顺利进行，高考镇实行了史上最严的“封锁”，在疫情开始之初，通往毛坦厂镇的所有道路都被切断，4月初，安徽省境内高三年级开始复课之后，因为外来复读学生较多，毛坦厂镇曾经短暂对外地家长和考生开放了几天，随之又实行严格管控，一直到7月5日考生离开高考镇时才解封。图为高考前，考生进入学校测量体温。也正是如此，今年的高考镇比往年低调了很多，连往年最热闹的送考节也没有举行，而是和其他所有普通学校一样，用几辆大巴将考生送到市区考试。图为往年的送考节。和衡水中学不一样，到高考镇来学习的都是普通考生，还有很多“落榜”生。有人说，高考镇太过严格，然而今年1万多人达本科线，意味着经过一年的努力，这一万多名考生或将改变命运，或许这就是高考镇存在的意义。'
sentence = '一个超过5000万人关注的大项目，您参加了吗?央视新闻新媒体推出武汉火神山、雷神山医院建设现场24小时不间断直播，短短几天时间吸引无数关心医院建设的网友围观。'

# 获取分词
seg_list = jieba.cut(sentence, cut_all=False)
print(' '.join(seg_list))
# 获取分词和词性
words = pseg.cut(sentence)
for word, flag in words:
	print('%s, %s' % (word, flag))


# 通过TF-IDF获取关键词
keywords = jieba.analyse.extract_tags(sentence, topK=20, withWeight=True, allowPOS=('n','nr','ns'))
for item in keywords:
    print(item[0],item[1])
print('-'*100)

# 基于TextRank算法的关键词抽取
#keywords = jieba.analyse.extract_tags(sentence, topK=20, withWeight=True, allowPOS=('n','nr','ns'))
#keywords = jieba.analyse.textrank(sentence, topK=20, withWeight=True, allowPOS=('ns', 'n', 'vn', 'v')) 
keywords = jieba.analyse.textrank(sentence, topK=20, withWeight=True, allowPOS=('n', 'ns')) 
#keywords = jieba.analyse.textrank(sentence, topK=20, withWeight=True) 
print(keywords)
for item in keywords:
    print(item[0],item[1])
