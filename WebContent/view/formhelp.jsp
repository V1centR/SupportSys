<jsp:include page="header.jsp" />
<body>
<div style="position:relative; float:left; height:100%; width:250px; background-color:#000; margin-right:20px; color:#fff; padding:15px;">Inicio</div>
<div style="position:relative; float:left;">
<form name="addHelp" class="addAtividade" method="POST" action="/supportSys/HelpController">
                <input type="hidden" id="formMode" name="form" value="true">
                <input type="hidden" id="idAtividade" name="idAtividade" value="aaa">
                <table class="table" border="0">
                    <tbody>
                        <tr>
                            <td colspan="2">                                
                                <label>*Título</label>                                
                                <input type="text" name="helpLabel" id="nameLabel" class="form-control" style="font-size: 14px; font-weight: bold; width: 500px;">                               
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                        	<td>
								<div style="position: relative; float: left; margin-right: 12px;">                                    
                                    <label>*Categoria</label>
                                    <select name="cat" class="form-control" style="width:250px;">
	                                    <option value="0">...</option>
	                                    <option value="1">Backup</option>
	                                    <option value="2">E-mail</option>
	                                    <option value="3">Hardware</option>
	                                    <option value="4">Impressora/Scanner</option>
	                                    <option value="5">Internet*</option>
	                                    <option value="6">Rede</option>
	                                    <option value="7">S.O</option>
	                                    <option value="8">Sistema</option>
	                                    <option value="9">Site</option>
	                                    <option value="10">Software</option>
	                                    <option value="11">Particular</option>
                                    </select>
                                </div>
							</td>
							<td>
								<div style="position: relative; float: left; margin-right: 12px;">                                    
                                    <label>*Setor</label>
                                    <select name="setor_name" class="form-control" style="max-width:250px; padding:10px;">
	        							<option value="" selected="selected">...</option>
	        							<option value="1">T.I</option>
	        							<option value="2">Assist Social Assist</option>
	        							<option value="3">Colônia</option>
	        							<option value="4">Cons Deliberativo</option>
	        							<option value="5">Cons Fiscal</option>
	        							<option value="6">Despachante</option>
	        							<option value="7">Ensino</option>
	        							<option value="8">Jurídico</option>
	        							<option value="9">Patrimônio</option>
	        							<option value="10">Pensionistas</option>
	        							<option value="11">Portaria</option>
	        							<option value="12">Relações Públicas</option>
	        							<option value="13">Salão de eventos</option>
	        							<option value="14">Secretaria</option>
	        							<option value="15">Tesouraria</option>
	        							<option value="16">Presidência</option>
	        							<option value="17">Vice Presidência</option>
        							</select>
                                </div>
							</td>
                        </tr>
                        <tr>
                        	<td>
                        	<label>*Telefone</label><br>
                        		<input type="text" class="form-control" name="phone">
                        	</td>
                        
                        </tr>
                        
                        <tr>
                            <td colspan="3">
                                <label>*Descrição</label><br>
                                <textarea class="form-control" name="desc" id="desc" maxlength="600" style="width: 600px; height: 150px; font-size: 12px; padding: 8px;"></textarea>
                            </td>
                        </tr>
                        <tr>
                           <td colspan="2" style="text-align:right">
                               <button id="voltar" type="button" name="btn" class="btn btn-primary btn-lg btn-voltar"><span class="glyphicon glyphicon-chevron-left"></span> Voltar</button>
                               <button id="enviar" type="submit" name="btn" class="btn btn-primary btn-lg btn-enviar"><span class="glyphicon glyphicon-fire"></span> Enviar</button>                                
                               <span class="loader"></span>
                           </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            </div>
</body>
</html>