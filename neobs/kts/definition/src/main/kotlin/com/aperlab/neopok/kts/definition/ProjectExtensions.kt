package com.aperlab.neobs.kts.definition

import com.aperlab.neobs.actions.CmdAction
import com.aperlab.neobs.model.Project
import com.aperlab.neobs.model.Target
import com.aperlab.neobs.model.Workspace
import com.aperlab.neobs.model.Target as TargetModel

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