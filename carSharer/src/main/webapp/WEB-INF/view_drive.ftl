<#include "header.ftl">
    <title> view_drive </title>
</head>
<body>
<#include "navbar.ftl">
    <div>
        <#if viewDrive??>
        	<#if viewDrive.status == "geschlossen">
				<#include "geschlossen.ftl">
            </#if>
        </#if>
        
          <#if viewDrive??>
        	<#if viewDrive.status == "offen">
				<#include "offen.ftl">
            </#if>
        </#if>
    </div>
</body>
</html>