package com.aperlab.neobs.hcl;

import com.aperlab.neobs.FileLoadingException;
import com.aperlab.neobs.ILoaderPlugin;

import com.aperlab.neobs.Runner;
import com.aperlab.neobs.hcl.parser.HclParser;
import com.aperlab.neobs.hcl.parser.Node;
import com.aperlab.neobs.model.Workspace;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class HclLoader implements ILoaderPlugin {

    Runner runnner;

    public HclLoader(Runner runnner) {
        this.runnner = runnner;
    }

    @Override
    public void LoadFile(File file) throws FileLoadingException {
        Node.ConfigFile results = null;

        try {
            results = new HclParser().parse(file);
        } catch (IOException e) {
            throw new FileLoadingException("Unable to load HCL file: " + file.getAbsolutePath(), e);
        }

        if(results.getBody().getDeclarations().isEmpty()){
            throw new FileLoadingException("No data found in HCL file: " + file.getAbsolutePath());
        }
        results.getBody().getDeclarations().forEach(declaration -> {
            if(declaration instanceof Node.Declaration.Block block){
                runnner.codecRegistry.get(block.getIdentifier().getLexeme()).ifPresent(codec -> {
                    var a = codec.decode(HclFormatOps.INSTANCE, block);
                    System.out.println(a);
                });
            }
        });
    }

    @Override
    public boolean CanLoad(File f) {
        return f.getName().endsWith(".hcl");
    }
}
