package com.aperlab.neobs.kts.definition

import com.aperlab.neobs.actions.CmdAction
import com.aperlab.neobs.model.AbstractProjectImpl
import com.aperlab.neobs.model.AbstractProjectImpl.AbstractProject
import com.aperlab.neobs.model.api.IProjectBuilder
import com.aperlab.neobs.model.Target


fun IProjectBuilder<*>.target(name: String, configure: Target.()->Unit){
    val t = Target(id.withTarget(name));
    configure.invoke(t);
    this.addTarget(t)
}