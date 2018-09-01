import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_BookInfo_booklist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/book/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',12,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',12,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
for( book in (bookList) ) {
printHtmlPart(6)
createTagBody(3, {->
expressionOut.print(book.title)
})
invokeTag('link','g',25,['action':("show"),'id':(book.id)],3)
printHtmlPart(7)
expressionOut.print(book.author.name)
printHtmlPart(8)
createClosureForHtmlPart(9, 3)
invokeTag('link','g',29,['action':("delete"),'id':(book.id)],3)
printHtmlPart(10)
}
printHtmlPart(11)
expressionOut.print(message)
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(13)
invokeTag('textField','g',37,['name':("title"),'value':(title)],-1)
printHtmlPart(14)
invokeTag('textField','g',38,['name':("author"),'value':(author)],-1)
printHtmlPart(15)
})
invokeTag('form','g',40,['name':("myForm"),'url':([controller:'book',action:'save'])],2)
printHtmlPart(4)
})
invokeTag('captureBody','sitemesh',41,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1466981128296L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
