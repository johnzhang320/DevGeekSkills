<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


 
 
 
<div id="overlay" class="web_dialog_overlay"></div>

<div id="uploadDialog" class="upload_dialog">
   <table style="width: 100%; border: 0px;" cellpadding="3" cellspacing="0">
      <tr>
         <td id="titleLine" class="web_dialog_title">Please select upload file from local disk</td>
         <td class="web_dialog_title align_right">
            <a href="#" id="btnPrmClose">X&nbsp;</a>
         </td>
      </tr>
      <tr>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
      </tr>
       
      <tr>
         <td colspan="3" style="padding-left: 15px; margin-top:20px;">
            <span id="uploadLine1" class="mySpan"></span><br>
              <input type="text" id="enteredLine" name="enteredLine" size="60" maxlength="51">
             <br><br> 
            <span id="uploadline2" class="mySpan"> </span>
             
         </td>
      </tr>
      <tr>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
      </tr>

      <tr>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
      </tr>
      <tr>
         <td colspan="2">
            <div class="buttonsCenter">
            <input id="btnUpldOk" type="button" class="button-left" value="Upload"/>
            <input id="btnUpldCancel" type="button" class="button-left" value="Cancel" />
            </div>
         </td>
      </tr>
   </table>
</div>