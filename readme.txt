1：项目说明
本项目为maven的父子结构项目，下载代码后从父项目节点导入子项目
2：本项目依赖activeMQ，所以需要在本地Windows或者Linux上安装activeMQ
3：修改项目的activeMQ配置文件
4：测试步骤：
4-1：启动activeMQ服务器
4-2：启动org.msg.subscribe.MQConsumer.main方法（消息订阅）
4-3：启动org.msg.publish.MQProducerMessage.main方法（消息发布）
==========================================================
特别说明1：
如果想要测试消息是否会丢失操作如下：
*1：消息发送到activeMQ服务器后，把服务器关闭，然后再次启动，此时观察
消息订阅者，如果可以接收到之前发送的消息则证明消息未丢失，否则丢失消息
特别说明2：
如果想要测试发送邮件操作如下：
*2-1：更改邮箱的配置文件
*2-2：消息订阅者去掉本行代码注释即可
//				bailBiz.mailSend(mailParam);