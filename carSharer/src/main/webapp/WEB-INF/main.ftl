<#include "header.ftl">
    <title> CarSharer </title>
</head>
<body>
<#include "navbar.ftl">
    <h1>
        CarSharer
    </h1>
    <h2>
        Meine reservierten fahrten
    </h2>
    <#list listUser as listUser>
    	<#if listUser.status == "geschlossen">
	    	<div>
	        <table class="pic-finger">
				<tr><td><p>
				<a href="view_drive?fid=${listUser.fid}">
				<img border="0" src="pic/${listUser.pic}" width="100" height="100" class="center">
				</a>
				</p></td></tr> 
	            <tr><td><p> Von:${listUser.startort} </p></td></tr>
	            <tr><td><p> Nach:${listUser.zielort} </p></td></tr>
	            <tr><td><p> Status:${listUser.status}</p></td></tr>
	        </table>
	       </div>
       </#if>
    </#list>
    
    <h2 style="clear: both;">
        Offen fahrten
    </h2>
    <#list listUser as listUser>
    	<#if listUser.status == "offen">
	    	<div>
	        <table class="pic-finger">
				<tr><td><p>
				<a href="view_drive?fid=${listUser.fid}">
				<img border="0" src="pic/${listUser.pic}" width="100" height="100" class="center">
				</a>
				</p></td></tr> 
	            <tr><td><p> Von:${listUser.startort} </p></td></tr>
	            <tr><td><p> Nach:${listUser.zielort} </p></td></tr>
	            <tr><td><p> Freie Plaetze:${listUser.anzPlaetze}</p></td></tr>
	            <tr><td><p> Fahrtkosten:${listUser.fahrtkosten}</p></td></tr>
	        </table>
	    	</div>
    	</#if>
    </#list>
    <#-- <div style="clear: both;position: relative;"><a href="new_drive" class="np"> Fahrt </a></div> -->
</body>
</html>