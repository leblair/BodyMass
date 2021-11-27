package com.example.bodymass

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bodymass.BodyMass.creff
import com.example.bodymass.BodyMass.lorentz
import com.example.bodymass.BodyMass.relativeFatMass
import com.example.bodymass.BodyMass.wanDerVael
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var radioGroup1: RadioGroup
    private lateinit var radioGroup2: RadioGroup
    private lateinit var radioGroup3: RadioGroup
    private lateinit var animatorT: ObjectAnimator
    private lateinit var animatorR: ObjectAnimator
    private lateinit var fatMass: RadioButton
    private lateinit var wanVael: RadioButton
    private lateinit var lorentz: RadioButton
    private lateinit var creff: RadioButton
    private lateinit var table: TableLayout
    private lateinit var textInput1: TextInputLayout
    private lateinit var height: TextInputEditText
    private lateinit var circumference: TextInputEditText
    private lateinit var textInput2: TextInputLayout
    private lateinit var resultBM: TextView
    private lateinit var calculate: Button
    private lateinit var male: RadioButton

    private var heightNum: Int = 0
    private var circumferenceNum: Int = 0
    private var bodyMass = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = findViewById(R.id.bodyMass)
        radioGroup1 = findViewById(R.id.radioGroup1)
        radioGroup2 = findViewById(R.id.radioGroup2)
        radioGroup3 = findViewById(R.id.radioGroup3)
        calculate = findViewById(R.id.calculate)
        fatMass = findViewById(R.id.fatMass)
        wanVael = findViewById(R.id.wanVael)
        lorentz = findViewById(R.id.lorentz)
        creff = findViewById(R.id.creff)
        male = findViewById(R.id.male)
        textInput1 = findViewById(R.id.textInput1)
        textInput2 = findViewById(R.id.textInput2)
        height = findViewById(R.id.height)
        circumference = findViewById(R.id.circumference)
        resultBM = findViewById(R.id.resultBM)
        table = findViewById(R.id.table)
        radioGroup2.setVisibility(View.GONE)
        textInput1.setVisibility(View.GONE)
        calculate.setVisibility(View.GONE)
        textInput2.setVisibility(View.GONE)
        resultBM.setVisibility(View.GONE)
        table.setVisibility(View.GONE)
        animatorT = ObjectAnimator.ofFloat(title, "alpha", 0f, 1f)
        animatorT.setDuration(1500)
        animatorR = ObjectAnimator.ofFloat(radioGroup1, "alpha", 0f, 1f)
        animatorR.setDuration(1500)
        val set = AnimatorSet()
        set.playTogether(animatorR, animatorT)
        set.start()
        val hint1 = resources.getString(R.string.waist)
        val hint2 = resources.getString(R.string.age_in_years)
        radioGroup1.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val selected = radioGroup1.getCheckedRadioButtonId()
            when (selected) {
                R.id.fatMass -> {
                    radioGroup2.setVisibility(View.VISIBLE)
                    textInput2.setVisibility(View.VISIBLE)
                    radioGroup3.setVisibility(View.GONE)
                    calculate.setVisibility(View.VISIBLE)
                    textInput1.setVisibility(View.VISIBLE)
                    resultBM.setVisibility(View.GONE)
                    table.setVisibility(View.GONE)
                    textInput2.setHint(hint1)
                    calculate.setOnClickListener(View.OnClickListener {
                        val selected2 = radioGroup2.getCheckedRadioButtonId()
                        when (selected2) {
                            R.id.male -> {
                                val heightNum =
                                    height.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                val circumferenceNum =
                                    circumference.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                val bodyMass = relativeFatMass(
                                    heightNum,
                                    circumferenceNum,
                                    GENDER.MALE
                                )
                                resultBM.setVisibility(View.VISIBLE)
                                table.setVisibility(View.VISIBLE)
                                resultBM.text = "$bodyMass%"
                            }
                            R.id.female -> {
                                heightNum = height.editableText.toString().trim { it <= ' ' }
                                    .toInt()
                                circumferenceNum =
                                    circumference.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                bodyMass = BodyMass.relativeFatMass(
                                    heightNum,
                                    circumferenceNum,
                                    GENDER.FEMALE
                                )
                                resultBM.setVisibility(View.VISIBLE)
                                table.setVisibility(View.VISIBLE)
                                resultBM.text = "$bodyMass%"
                            }
                        }
                    })
                }
                R.id.wanVael -> {
                    radioGroup2.setVisibility(View.VISIBLE)
                    textInput2.setVisibility(View.GONE)
                    radioGroup3.setVisibility(View.GONE)
                    calculate.setVisibility(View.VISIBLE)
                    textInput1.setVisibility(View.VISIBLE)
                    resultBM.setVisibility(View.GONE)
                    table.setVisibility(View.GONE)
                    calculate.setOnClickListener(View.OnClickListener {
                        val selected2 = radioGroup2.getCheckedRadioButtonId()
                        when (selected2) {
                            R.id.male -> {
                                val heightNum =
                                    height.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                val bodyMass = wanDerVael(heightNum, GENDER.MALE)
                                resultBM.setVisibility(View.VISIBLE)
                                table.setVisibility(View.GONE)
                                resultBM.text = "$bodyMass Kg"
                            }
                            R.id.female -> {
                                heightNum = height.editableText.toString().trim { it <= ' ' }
                                    .toInt()
                                bodyMass = wanDerVael(heightNum, GENDER.FEMALE)
                                resultBM.setVisibility(View.VISIBLE)
                                table.setVisibility(View.GONE)
                                resultBM.text = "$bodyMass Kg"
                            }
                        }
                    })
                }
                R.id.lorentz -> {
                    radioGroup2.setVisibility(View.VISIBLE)
                    textInput2.setVisibility(View.VISIBLE)
                    radioGroup3.setVisibility(View.GONE)
                    calculate.setVisibility(View.VISIBLE)
                    textInput1.setVisibility(View.VISIBLE)
                    resultBM.setVisibility(View.GONE)
                    table.setVisibility(View.GONE)
                    textInput2.setHint(hint2)
                    calculate.setOnClickListener(View.OnClickListener {
                        val selected2 = radioGroup2.getCheckedRadioButtonId()
                        when (selected2) {
                            R.id.male -> {
                                val heightNum =
                                    height.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                val circumferenceNum =
                                    circumference.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                val bodyMass = lorentz(
                                    heightNum,
                                    circumferenceNum,
                                    GENDER.MALE
                                )
                                resultBM.setVisibility(View.VISIBLE)
                                table.setVisibility(View.GONE)
                                resultBM.text = "$bodyMass Kg"
                            }
                            R.id.female -> {
                                heightNum = height.editableText.toString().trim { it <= ' ' }
                                    .toInt()
                                circumferenceNum =
                                    circumference.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                bodyMass = lorentz(
                                    heightNum,
                                    circumferenceNum,
                                    GENDER.FEMALE
                                )
                                resultBM.setVisibility(View.VISIBLE)
                                table.setVisibility(View.GONE)
                                resultBM.text = "$bodyMass Kg"
                            }
                        }
                    })
                }
                R.id.creff -> {
                    radioGroup2.setVisibility(View.GONE)
                    textInput2.setVisibility(View.VISIBLE)
                    textInput2.setHint(hint2)
                    radioGroup3.setVisibility(View.VISIBLE)
                    calculate.setVisibility(View.VISIBLE)
                    textInput1.setVisibility(View.VISIBLE)
                    resultBM.setVisibility(View.GONE)
                    table.setVisibility(View.GONE)
                    calculate.setOnClickListener(View.OnClickListener {
                        val selected2 = radioGroup3.getCheckedRadioButtonId()
                        when (selected2) {
                            R.id.small -> {
                                val heightNum =
                                    height.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                val circumferenceNum =
                                    circumference.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                val bodyMass = creff(
                                    heightNum,
                                    circumferenceNum,
                                    Morphology.SMALL
                                )
                                resultBM.setVisibility(View.VISIBLE)
                                table.setVisibility(View.GONE)
                                resultBM.text = "$bodyMass Kg"
                            }
                            R.id.medium -> {
                                heightNum = height.editableText.toString().trim { it <= ' ' }
                                    .toInt()
                                circumferenceNum =
                                    circumference.editableText.toString().trim { it <= ' ' }
                                        .toInt()
                                bodyMass = creff(
                                    heightNum,
                                    circumferenceNum,
                                    Morphology.MEDIUM
                                )
                                resultBM.setVisibility(View.VISIBLE)
                                table.setVisibility(View.GONE)
                                resultBM.text = "$bodyMass Kg"
                            }
                            R.id.broad -> {
                                heightNum = height.getEditableText().toString().trim { it <= ' ' }
                                    .toInt()
                                circumferenceNum =
                                    circumference.getEditableText().toString().trim { it <= ' ' }
                                        .toInt()
                                bodyMass = creff(
                                    heightNum,
                                    circumferenceNum,
                                    Morphology.BROAD
                                )
                                resultBM.setVisibility(View.VISIBLE)
                                table.setVisibility(View.GONE)
                                resultBM.text = "$bodyMass Kg"
                            }
                        }
                    })
                }
            }
        }
        )
    }
}