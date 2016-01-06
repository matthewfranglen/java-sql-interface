<#import "util-methods.ftl" as utilMethods>

<#macro methods takes returns>
<#if takes.defined && returns.defined>

<#elseif returns.defined>

<#elseif takes.defined>
    <#if takes.empty>

    <#elseif takes.singular>

    public int[] batchUpdate(${takes.first.type.javaType}[] batchValues) {
        java.util.Map<String,?>[] parameters = new java.util.Map<String,?>[batchValues.length];
        for (int i = 0; i < batchValues.length; i++) {
            parameters[i] = java.util.Collections.singletonMap("${takes.first.name}", batchValues[i]);
        }
        return template.batchUpdate(getStatement(), parameters);
    }

    <#else>

    public int[] batchUpdate(${takes.type.javaType}[] batchValues) {
        java.util.Map<String,?>[] parameters = new java.util.Map<String,?>[batchValues.length];
        for (int i = 0; i < batchValues.length; i++) {
            parameters[i] = new java.util.HashMap<>();
            <#list takes.iterator() as argument>
            parameters[i].put("${argument.name}", ${argument.name});
            </#list>
        }
        return template.batchUpdate(getStatement(), parameters);
    }

    </#if>
<#else>

    public int[] batchUpdate(java.util.Map<String,?>[] batchValues) {
        return template.batchUpdate(getStatement(), batchValues);
    }

    public int[] batchUpdate(org.springframework.jdbc.core.namedparam.SqlParameterSource[] batchArgs) {
        return template.batchUpdate(getStatement(), batchArgs);
    }

</#if>
</#macro>
