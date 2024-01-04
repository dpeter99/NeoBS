package com.aperlab.neopok.kts.definition

import com.aperlab.neopok.model.Project
import com.aperlab.neopok.model.Target as TargetModel

fun Project.Target(name: String){
    val t = TargetModel();

    this.AddTarget(t)
}