package me.geek.mail.Modules

import me.geek.mail.Configuration.ConfigManager
import me.geek.mail.api.hook.hookPlugin
import me.geek.mail.api.mail.MailManage
import me.geek.mail.common.DataBase.DataManage
import me.geek.mail.api.mail.MailSub
import java.util.UUID
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack

/**
 * 作者: 老廖
 * 时间: 2022/8/6
 */
class Mail_Text(
    override val mailID: UUID,
    override var title: String,
    override var text: String,
    override var sender: UUID,
    override var target: UUID,
    override var state: String = "无",
    override val mailType: String,

    override val additional: String,
    override val appendixInfo: String,
    override val senderTime: String,
    override var getTime: String,

    ) : MailSub() {

    constructor(mailID: UUID, Title: String, Text: String, sende: UUID, targe: UUID, state: String, no: String) : this(
        mailID = mailID,
        title = Title,
        text = Text,
        sender = sende,
        target = targe,
        state = "无",
        mailType = "文本邮件",
        appendixInfo = "",
        additional = no,
        senderTime = System.currentTimeMillis().toString(),
        getTime = ""

    )
    constructor(mailID: UUID, Title: String, Text: String, sende: UUID, targe: UUID, state: String, no: String, item: Any?, command: Any?, time: Array<Any>) : this(
        mailID = mailID,
        title = Title,
        text = Text,
        sender = sende,
        target = targe,
        state = "无",
        mailType = "文本邮件",
        appendixInfo = "",
        additional = no,
        senderTime = time[0].toString(),
        getTime = time[1].toString()
    )

    override fun sendMail() {
        if (sender == ConfigManager.Console) {
            val targets = Bukkit.getPlayer(target)
            DataManage.insert(this, null)
            if (targets != null) {
                MailManage.addTargetCache(target, this)
            }
            MailManage.sendMailMessage(this.title, this.text, null, targets)
        } else {
            super.sendMail()
        }
    }

    override fun giveAppendix() {
    }
}