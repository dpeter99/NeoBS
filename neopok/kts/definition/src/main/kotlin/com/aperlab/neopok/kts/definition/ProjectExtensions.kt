package com.aperlab.neopok.kts.definition

import com.aperlab.neopok.actions.CmdAction
import com.aperlab.neopok.model.Project
import com.aperlab.neopok.model.Target
import com.aperlab.neopok.model.Workspace
import com.aperlab.neopok.model.Target as TargetModel

fun Project.Target(name: String, configure: TargetModel.()->Unit){
    val t = TargetModel();
    configure.invoke(t);
    this.AddTarget(t)
}

fun Target.CmdAction(configure: CmdAction.()->Unit){
    val a = CmdAction()
    configure.invoke(a);
    actions.add(a);
}