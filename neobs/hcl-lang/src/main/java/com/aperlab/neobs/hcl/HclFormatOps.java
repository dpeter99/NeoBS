package com.aperlab.neobs.hcl;

import com.aperlab.neobs.hcl.parser.Node;
import com.aperlab.serialization.DataResult;
import com.aperlab.serialization.FormatOps;

public class HclFormatOps implements FormatOps<Node> {

    public static final HclFormatOps INSTANCE = new HclFormatOps();

    @Override
    public Node empty() {
        return null;
    }

    @Override
    public DataResult<? extends Node> get(String key, Node input) {
        if(input instanceof Node.Declaration.Block block){
            return block.getBody().getDeclarations().stream()
                    .filter(d -> d instanceof Node.Declaration.Attribute attribute && attribute.getIdentifier().getLexeme().equals(key))
                    .findFirst()
                    .map(DataResult::ofSuccess)
                    .orElseGet(() -> DataResult.ofError("No such key: " + key));
        }
        return DataResult.ofError("Node is not a block");
    }

    @Override
    public DataResult<String> getStringValue(Node input) {
        if(input instanceof Node.Declaration.Attribute attribute){
            return DataResult.ofSuccess(attribute.getValue().getValue());
        }
        return DataResult.ofError("Node is not an attribute");
    }

    @Override
    public DataResult<String> getAttributeString(Node input, String key) {
        if(input instanceof Node.Declaration.Block block){
            return get(key, input).map((i)->{
                if(i instanceof Node.Declaration.Attribute attribute){
                    return attribute.getValue().getValue();
                }
                return null;
            });
        }
        return DataResult.ofError("Node is not a block");
    }

    @Override
    public DataResult<Integer> getAttributeInt(Node input, String key) {
        return null;
    }

    @Override
    public DataResult<String> requireAttributeString(Node input, String key) {
        var name = getAttributeString(input, key);
        if (name.isError()) {
            throw new RuntimeException("No attribute for key: " + key);
        }
        return name;
    }

    @Override
    public DataResult<String> getLabel(Node input, int i) {
        if(input instanceof Node.Declaration.Block block){
            return DataResult.ofSuccess(block.getLabels().get(i).getLexeme());
        }
        return DataResult.ofError("Node is not a block");
    }

    @Override
    public DataResult<String> requireLabel(Node input, int i) {
        var name = getLabel(input, i);
        if (name.isError()) {
            throw new RuntimeException("No label at position:" + i);
        }
        return name;
    }


}
