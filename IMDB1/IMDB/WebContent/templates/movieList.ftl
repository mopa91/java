    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Movie List </title>
			<#include "header.ftl">
    <h2>
        Movies Information
    </h2>
    <#list webAppUser as info>
    	<div>
        <table class="pic-finger">
            <tr><td><p> title:${info.title} </p></td></tr>
            <tr><td><p> director:${info.director} </p></td></tr>
            <tr><td><p> actor:${info.actor}</p></td></tr>
            <#-- added publish date as well -->
            <tr><td><p> Publish date:${info.publishDate}</p></td></tr>
            <#-- end -->
            <tr><td><a href="rate?title=${info.title}&username=${username}">  rate this movie </a></td></tr>
        </table>
       </div>
    </#list>
    
</body>
</html>