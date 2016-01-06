<#macro argumentsToParameters takes>
<#if takes.empty>
    Map<String, ?> parameters = java.util.Collections.emptyMap();
<#elseif takes.singular>
    Map<String, ?> parameters = java.util.Collections.singletonMap("${takes.first.name}", ${takes.first.name});
<#else>
    Map<String, ?> parameters = new java.util.HashMap<>();
    <#list takes.iterator() as argument>
    parameters.put("${argument.name}", ${argument.name});
    </#list>
</#if>
</#macro>

<#macro instanceToParameters takes>
    Map<String, ?> parameters = new java.util.HashMap<>();
    <#list takes.iterator() as argument>
    parameters.put("${argument.name}", takes.${argument.getterName}());
    </#list>
</#macro>

