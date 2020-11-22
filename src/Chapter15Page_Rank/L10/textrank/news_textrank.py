#-*- encoding:utf-8 -*-
from textrank4zh import TextRank4Keyword, TextRank4Sentence
import jieba

text = '一个超过5000万人关注的大项目，您参加了吗?央视新闻新媒体推出武汉火神山、雷神山医院建设现场24小时不间断直播，短短几天时间吸引无数关心医院建设的网友围观。其中最受广大网友关注的火神山两路镜头对准的正是中建三局三公司承建区域，截至30日13:50，在看人数已突破5000万。热心的网友们自称为“云监工”或“网络包工头”，自发在评论区实行“两班倒”打卡“监督”还热切、尽职的互动交流起了“工作”。这一称号数度登上微博热搜。繁忙却相对单调枯燥的施工画面，因为“云监工”们脑洞大开的评论而妙趣横生，他们给所有的机器都起好了名字，根据颜色亲昵的叫上了小红、小黄、小蓝，没事就来视频里蹲自己喜欢的机器出现还编起了段子，绘起了同人。不仅现场的各种机械设备有了“粉丝团”，有细心者甚至数出了直播画面中中建三局三公司这一施工区域中出镜的管理人员与工友人数。记者了解到，火神山医院建设方之一的中建三局三公司在四川也有不少项目，德阳市妇女儿童专科医院项目也是该公司在建设。最后，让我们一起以比直播镜头更近、更鲜活的视角来重温让万千网友为之惊叹鼓舞的奋斗时刻!'

# 输出关键词，设置文本小写，窗口为2
tr4w = TextRank4Keyword()
tr4w.analyze(text=text, lower=True, window=3)
print('关键词：')
for item in tr4w.get_keywords(20, word_min_len=2):
    print(item.word, item.weight)


# 输出重要的句子
tr4s = TextRank4Sentence()
tr4s.analyze(text=text, lower=True, source = 'all_filters')
print('摘要：')
# 重要性较高的三个句子
for item in tr4s.get_key_sentences(num=3):
	# index是语句在文本中位置，weight表示权重
    print(item.index, item.weight, item.sentence)
