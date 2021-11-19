package dev.kingbond.notify.ui.about

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.notify.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element


class AboutActivity : AppCompatActivity() {


    private val emailId = "kingbond470@gmail.com"
    private val emailIdAbhi = "abhimanyumishra130@gmail.com"
    private val emailIdMurali = "murali.sundara@ssipmt.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val versionElement: Element =
            Element().setTitle("Version 1.0").setIconDrawable(R.drawable.ic_tools)
                .setIconTint(R.color.navigation_color)

        val linkedInIntent = Intent(Intent.ACTION_VIEW)
        linkedInIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data = Uri.parse((String.format("https://www.linkedin.com/in/mausam-singh-5073451ab")))
        }

        val linkedInIntentMurali = Intent(Intent.ACTION_VIEW)
        linkedInIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data =
                Uri.parse((String.format("https://www.linkedin.com/in/murali-krishna-sundara-607749169/")))
        }

        val linkedInIntentAbhi = Intent(Intent.ACTION_VIEW)
        linkedInIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data =
                Uri.parse((String.format("https://www.linkedin.com/in/abhimanyu-mishra-0545431a1/")))
        }

        val instaIntent = Intent(Intent.ACTION_VIEW)
        instaIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data = Uri.parse((String.format("https://instagram.com/computer_science__student")))
        }

        val instaIntentMurali = Intent(Intent.ACTION_VIEW)
        instaIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data = Uri.parse((String.format("https://instagram.com/murali__krishna__")))
        }

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data = Uri.parse("mailto:$emailId")
            putExtra(Intent.EXTRA_SUBJECT, "Implicit Intents")
        }

        val emailIntentMurali = Intent(Intent.ACTION_SENDTO)
        emailIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data = Uri.parse("mailto:$emailIdMurali")
            putExtra(Intent.EXTRA_SUBJECT, "Implicit Intents")
        }

        val emailIntentAbhi = Intent(Intent.ACTION_SENDTO)
        emailIntent.apply {
            addCategory(Intent.CATEGORY_BROWSABLE);
            data = Uri.parse("mailto:$emailIdAbhi")
            putExtra(Intent.EXTRA_SUBJECT, "Implicit Intents")
        }

        val linkedIn: Element = Element().setTitle("Mausam")
            .setIconDrawable(R.drawable.ic_linkedin_circle).setIntent(linkedInIntent)

        val instagram: Element = Element().setTitle("Mausam")
            .setIconDrawable(R.drawable.ic_instagram).setIntent(instaIntent)

        val email: Element = Element().setTitle("Mausam")
            .setIconDrawable(R.drawable.ic_email_about).setIntent(emailIntent)
            .setIconTint(R.color.yellow)

        val linkedInMurali: Element = Element().setTitle("Murali")
            .setIconDrawable(R.drawable.ic_linkedin_circle).setIntent(linkedInIntentMurali)

        val instagramMurali: Element = Element().setTitle("Murali")
            .setIconDrawable(R.drawable.ic_instagram).setIntent(instaIntentMurali)

        val emailMurali: Element = Element().setTitle("Murali")
            .setIconDrawable(R.drawable.ic_email_about).setIntent(emailIntentMurali)
            .setIconTint(R.color.yellow)

        val linkedInAbhi: Element = Element().setTitle("Abhimanyu")
            .setIconDrawable(R.drawable.ic_linkedin_circle).setIntent(linkedInIntentAbhi)

        val emailAbhi: Element = Element().setTitle("Abhimanyu")
            .setIconDrawable(R.drawable.ic_email_about).setIntent(emailIntentAbhi)
            .setIconTint(R.color.yellow)

        val aboutPage = AboutPage(this)
            .setImage(R.drawable.ic_welcome)
            .setDescription("This is a Notify android application developed by Abhimanyu, Murali and Mausam. We provide the notification and alarm on task, event and goal. So that you will be more productive #BeProductive")
            .addGroup("Connect with Us")
            .addGitHub("kingbond470/Notify", "Github")
            .addGroup("Connect with Mausam")
            .addItem(linkedIn)
            .addItem(instagram)
            .addItem(email)
            .addGroup("Connect with Murali")
            .addItem(linkedInMurali)
            .addItem(instagramMurali)
            .addItem(emailMurali)
            .addGroup("Connect with Abhimanyu")
            .addItem(linkedInAbhi)
            .addItem(emailAbhi)
            .addGroup("About Notify")
            .addItem(versionElement)
            .create()
        setContentView(aboutPage)
    }
}