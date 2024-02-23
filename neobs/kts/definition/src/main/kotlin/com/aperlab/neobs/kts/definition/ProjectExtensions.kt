package com.aperlab.neobs.kts.definition

import com.aperlab.neobs.model.Target


fun IProjectBuilder<*>.target(name: String, configure: Target.()->Unit){
    val t = Target(id.withTarget(name));
    configure.invoke(t);
    this.addTarget(t)
}