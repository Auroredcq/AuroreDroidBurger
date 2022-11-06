package fr.isen.debacq.auroredroidburger

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    companion object{ const val sharedPrefFile = "nicolasdroidburger" }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Declaration des variables
        val burger_list = resources.getStringArray(R.array.burger_list)
        val spinner = findViewById<Spinner>(R.id.burger)
        val heure = findViewById<Button>(R.id.heure)
        val button = findViewById<Button>(R.id.button)
        val Nom = findViewById<EditText>(R.id.Nom)
        val Prenom = findViewById<EditText>(R.id.Prenom)
        val numeroRue = findViewById<EditText>(R.id.numeroRue)
        val Rue = findViewById<EditText>(R.id.Rue)
        val CodePostal = findViewById<EditText>(R.id.CodePostal)
        val villeLivraison = findViewById<EditText>(R.id.villeLivraison)
        val numeroTelephone = findViewById<EditText>(R.id.numeroTelehone)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        var burger_choisi : String = "BurgerError"
        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)
        val intent = Intent(this, ConfirmationActivity::class.java)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, burger_list)

        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                heure.text=(String.format("%d : %d", hourOfDay, minute))
            }
        }, hour, minute, true)

        heure.setOnClickListener({ mTimePicker.show() })

        button.setOnClickListener {
            if (heure.text.equals("Select Time") || heure.text.isEmpty() || Prenom.text.isEmpty() || Nom.text.isEmpty() || Rue.text.isEmpty() || numeroTelephone.text.isEmpty() || numeroTelephone.text.length != 10 || burger_choisi.equals("Sélectionnez un Burger") || villeLivraison.text.isEmpty() || CodePostal.text.isEmpty()){
                Toast.makeText(this@MainActivity, "Veuillez remplir tout les champs svp.", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@MainActivity, "Confirmation", Toast.LENGTH_SHORT).show()
                val saveNom:String = Nom.text.toString()
                val savePrenom:String = Prenom.text.toString()
                val saveNumeroRue:String = numeroRue.text.toString()
                val saveRue:String = Rue.text.toString()
                val saveCodePostal2:String = CodePostal.text.toString()
                val saveVille : String = villeLivraison.text.toString()
                val saveTelephone:String = numeroTelephone.text.toString()
                val saveBurger: String = burger_choisi
                val saveHeure:String = heure.text.toString()
                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                editor.putString("savePrenom_key",savePrenom)
                editor.putString("saveNom_key",saveNom)
                editor.putString("saveNumeroRue_key",saveNumeroRue)
                editor.putString("saveRue_key",saveRue)
                editor.putString("saveCodePostal_key",saveCodePostal2)
                editor.putString("saveVille_key",saveVille)
                editor.putString("saveTelephone_key",saveTelephone)
                editor.putString("saveBurger_key",saveBurger)
                editor.putString("saveHeure_key",saveHeure)
                editor.apply()
                editor.commit()
                System.out.println(sharedPreferences.all)
                startActivity(intent)
            }
        }

            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected( parent: AdapterView<*>, view: View, position: Int, id: Long ) {
                    burger_choisi = burger_list[position]
                    Toast.makeText(this@MainActivity, getString(R.string.selected_item) + burger_list[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }

        }
    }
}