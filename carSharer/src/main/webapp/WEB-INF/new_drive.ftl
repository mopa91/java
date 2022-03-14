<#include "header.ftl">
    <title> Fahrt erstellen </title>
</head>
<body>
<#include "navbar.ftl">
    <div class="new_wrapper">
	<form action="/new_drive" method="post">
		        <table>
            <h1 class="main">
                Fahrt erstellen
            </h1>
            <#if errors??>
             <h3 style="color: red;text-align: center;">
				${errors}
            </h3>
            </#if> 
            <tr>
                <td><label for=""> Von: </label></td>
                <td><input type="text" placeholder="Startort" name="startort">
                <input type="hidden" name="benutzerID" value="${benutzerID}">
            </tr>
            <tr>
                <td><label for=""> Nach: </label></td>
                <td><input type="text" placeholder="Zielort" name="zielort"></td>
            </tr>
            <tr>
                <td><label for=""> Maximale kapazitaet: </label></td>
                <td><input type="number" name="maxPlaetze" min="1" max="10" required></td>
            </tr>
            <tr>
                <td><label for=""> Fahrtkosten: </label></td>
                <td><input type="number" placeholder="Fahrtkosten" name="fahrtkosten" min = "1" required> € </td>
            </tr>
            <tr>
                <td><label for=""> Transportmittel: </label></td>
                <td>
                    <input type="radio" value="1" name="transportmittel">
                    <label for="">Auto</label>
                    <input type="radio" value="2" name="transportmittel">
                    <label for="">Bus</label>
                    <input type="radio" value="3" name="transportmittel">
                    <label for="">Kleintransporter</label>
                </td>
            </tr>
            <tr>
                <td><label for=""> Fahrtdatum: </label></td>
                <td>
                    <input type="date" name="date" min="${time}" max = "2036-09-30" required>
                    <input type="time" name="time" required>
                </td>
            </tr>
            <tr>
                <td><label for=""> Beschreibung: </label></td>
                <td><textarea name="beschreibung" cols="30" rows="10" maxlength="50"></textarea></td>
            </tr>
            <tr>
                <td><button class="new_button" value="submit"> Erstellen </button></td>
            </tr>
        </table>
	</form>
    </div>
</body>
</html>