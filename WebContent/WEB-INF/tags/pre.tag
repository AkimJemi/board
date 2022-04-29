<%@ tag body-content="empty" pageEncoding="utf-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="value" required="true" type="java.lang.String" %>
<%
value=value.replace("\n", "\n<br>");
value=value.replace("&", "&amp;");
value=value.replace("<", "&It;");
value=value.replace(" ", "&nbsp;");

%>
<%= value%>