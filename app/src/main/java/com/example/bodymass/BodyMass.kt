package com.example.bodymass

import com.example.bodymass.GENDER
import com.example.bodymass.Morphology

object BodyMass {
    fun relativeFatMass(height: Int, waist: Int, gender: GENDER): Double {
        return Math.round(if (gender == GENDER.MALE) Constants.nMen - (20 * height / waist).toDouble() else Constants.nWomen - (20 * height / waist).toDouble()) * 100.0 / 100
    }

    fun wanDerVael(height: Int, gender: GENDER): Double {
        return Math.round(if (gender == GENDER.MALE) (height - 150) * Constants.mMen + 50 else (height - 150) * Constants.mWomen + 50) * 100.0 / 100
    }

    fun lorentz(height: Int, age: Int, gender: GENDER): Double {
        return Math.round(if (gender == GENDER.MALE) height - 100 - ((height - 150) / 4).toDouble() + (age - 20) / Constants.kMen else height - 100 - ((height - 150) / 4).toDouble() + (age - 20) / Constants.kWomen) * 100.0 / 100
    }

    fun creff(height: Int, age: Int, morph: Morphology): Double {
        val result: Double
        result = if (morph == Morphology.SMALL) {
            (height - 100 + age / 10).toDouble() * Math.pow(0.9, 2.0)
        } else if (morph == Morphology.MEDIUM) {
            (height - 100 + age / 10).toDouble() * 0.9
        } else {
            (height - 100 + age / 10).toDouble() * 0.9 * 1.1
        }
        return Math.round(result * 100.0) / 100.0
    }
}