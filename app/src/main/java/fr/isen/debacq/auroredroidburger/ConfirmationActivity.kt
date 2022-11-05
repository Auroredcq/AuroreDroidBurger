package fr.isen.debacq.auroredroidburger

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import fr.isen.debacq.auroredroidburger.MainActivity.Companion.sharedPrefFile

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        //declaration des variables
        val textView4 = findViewById<TextView>(R.id.textView4)
        val button2 = findViewById<Button>(R.id.button2)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val Nom: String? = sharedPreferences.getString( "saveNom_key","NomError" )
        val Prenom: String? = sharedPreferences.getString( "savePrenom_key","PrenomError" )
        val Adresse: String? = sharedPreferences.getString( "saveAdresse_key","AdresseError" )
        val numeroTelephone: String? = sharedPreferences.getString( "saveTelephone_key","TelephoneError" )
        val Burger: String? = sharedPreferences.getString( "saveBurger_key","BurgerError" )
        val livraisonHeure: String? = sharedPreferences.getString( "saveHeure_key","HeureError" )
        val emailEnvoi = Intent(Intent.ACTION_SEND)

        textView4.text = "Merci  Mr/Mme ${Nom} ${Prenom} pour votre commande du ${Burger} \n\ril vous sera livré à l'adresse :\r\n ${Adresse} \r\nau plus tard à ${livraisonHeure} \r\nNotre livreur vous appellera au ${numeroTelephone}"

        fun envoidEmail(Destinataire: String, objet: String, corps: String) {

            emailEnvoi.data = Uri.parse("mailto:")
            emailEnvoi.type = "text/plain"
            emailEnvoi.putExtra(Intent.EXTRA_EMAIL, arrayOf(Destinataire))
            emailEnvoi.putExtra(Intent.EXTRA_SUBJECT, objet)
            emailEnvoi.putExtra(Intent.EXTRA_TEXT, corps)

            try {
                startActivity(Intent.createChooser(emailEnvoi, "Send Email"))
            }catch (ex: ActivityNotFoundException) {
            }
        }
        button2.setOnClickListener {
            envoidEmail("Marc.mollinari@gmail.com","Confirmation commande","Votre commande a bien été enregistrée")

         }
    }
}