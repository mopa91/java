                <div class="view_wrapper" style="position: relative;">
                    <table> 
                    <h1 class="main">
                        Information 
                    </h1>
                    <#if error??>
             			<h3 style="color: red;text-align: center;">
							${error}
            			</h3>
            		</#if> 
                    <img src="pic/${viewDrive.pic}" width="100" height="100" class="center">
                    <tr>
                        <td><label for=""> Anbieter: </label></td>
                        <td>${viewDrive.anbieter}</td>
                    </tr>
                    <tr>
                        <td><label for=""> Fahrtdatum und -uhrzeit: </label></td>
                        <td>${viewDrive.fahrtdatumzeit}</td>
                    </tr>
                    <tr>
                        <td><label for=""> Von: </label></td>
                        <td> ${viewDrive.startort} </td>
                    </tr>
                    <tr>
                        <td><label for=""> Nach: </label></td>
                        <td>${viewDrive.zielort}</td>
                    </tr>
                    <tr>
                     	<td><label for=""> Anzahl freier plaetze: </label></td>
                        <td> ${viewDrive.anzPlaetze} </td>
                    </tr>
                    <tr>
                        <td><label for=""> Fahrkosten: </label></td>
                        <td> ${viewDrive.fahrtkosten} $ </td>
                    </tr>
                    <tr>
                        <td><label for=""> Status: </label></td>
                        <td> ${viewDrive.status} </td>
                    </tr>
                    <tr>
                    <#if viewDrive.beschreibung??>
                        <td><label for=""> Beschreibung: </label></td>
                        <td><textarea name="" id="" cols="30" rows="10">${viewDrive.beschreibung}</textarea></td>
                    <#else>
                        <td><label for=""> Beschreibung: </label></td>
                        <td><textarea name="" id="" cols="30" rows="10"></textarea></td>
                    </#if>
                    </tr>
                    <th></th>
                </table>
                <hr>
                <h1 class="main">
                    Aktionsliste
                </h1>
                <form action="view_drive?fid=${viewDrive.fahrtId}" method="post">
                	<span style="margin-right: 5px;">
                        <label for=""> Anzahl plaetze fuer Reservierung: </label>
                        <#if viewDrive.anzPlaetze == 1>
                         	<select name="anzPlaetze">
                                <option value="1">1</option>
                         <#else>
                         	 <select name="anzPlaetze">
                                <option value="1">1</option>
                                <option value="2">2</option>
                         </#if>
                           	</select> 
                           	<input type="hidden" name="fid" value="${viewDrive.fahrtId}">
                           	<input type="hidden" name="benutzerId" value="${benutzerId}">
                				<button value="submit" class="reservierung"> Fahrt reservieren </button>
                			<div style="margin: 20px; margin-left: 310px;">
                    			<a href="delete?fid=${viewDrive.fahrtId}" class="loeschen" > Fahrt loeschen </a>
                			</div>
                	</span>
                </form> 
                <hr>
                <div>
                    <div style="font-size: 22px; float: left;margin-top: 10px;">
                        Bewertungen
                    </div>
                    <#if get_MovieOverview??>
                    <div style="font-size: 22px;float: right;margin-top: 10px;">
                        Durchschnittsrating: ${get_MovieOverview}
                    </div>
                    </#if>
                </div>


				<#list schreibenDrive as schreibenDrive>
					<div style="clear: both;">
	                    <div style="float: left;margin-left: 10px; margin-bottom: 15px; margin-top: 10px;">
	                        ${schreibenDrive.benutzer}
	                    </div>
	                    <div style="float: left;margin-left: 50px;margin-top: 10px;">
	                        ${schreibenDrive.textnachricht}
	                    </div>
	                    <div style="float: left;margin-left: 80px;margin-top: 10px;">
	                        ${schreibenDrive.rating}
	                    </div>
                	</div>
				</#list>
                
                <div style="clear: both;position: relative;float: right;">
                    <a href="new_rating?fid=${viewDrive.fahrtId}" class="reservierung"> Fahrt bewerten </a>
                </div>
                <div style="clear: both;"></div>
            </div>