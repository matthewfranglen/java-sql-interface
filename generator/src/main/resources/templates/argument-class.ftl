<#macro define interface name>
<#if interface.multiple>
    public static final class ${name} {
    <#list interface.iterator() as argument>
        private final ${argument.type.javaType} ${argument.name};
    </#list>

        public ${name}(${interface.parameters}) {
        <#list interface.iterator() as argument>
            this.${argument.name} = ${argument.name};
        </#list>
        }

    <#list interface.iterator() as argument>
        public ${argument.type.javaType} ${argument.getterName}() {
            return ${argument.name};
        }
    </#list>
    }
</#if>
</#macro>
