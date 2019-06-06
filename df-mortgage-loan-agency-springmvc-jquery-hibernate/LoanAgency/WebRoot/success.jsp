<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style>
        .tablescroll
{ font: 12px normal Tahoma, Geneva, "Helvetica Neue", Helvetica, Arial, sans-serif; background-color:#fff; }

.tablescroll td, 
.tablescroll_wrapper,
.tablescroll_head,
.tablescroll_foot
{ border:1px solid #ccc; }

.tablescroll td
{ padding:3px 5px; }

.tablescroll_wrapper
{ border-left:0; }

.tablescroll_head
{ font-size:11px; font-weight:bold; background-color:#eee; border-left:0; border-top:0; margin-bottom:3px; }

.tablescroll thead td
{ border-right:0; border-bottom:0; }

.tablescroll tbody td
{ border-right:0; border-bottom:0; }

.tablescroll tbody tr.first td
{ border-top:0; }

.tablescroll_foot
{ font-weight:bold; background-color:#eee; border-left:0; border-top:0; margin-top:3px; }

.tablescroll tfoot td
{ border-right:0; border-bottom:0; }
        
     </style>
  </head>
  
  <body>
   <table id="thetable" cellspacing="0">
<thead>
    <tr>
      <td>city</td>
      <td>state code</td>
      <td>zip</td>
      <td>latitude</td>
      <td>longitude</td>
      <td>county</td>
    </tr>
</thead>
<tfoot>
    <tr>
      <td>city</td>
      <td>state code</td>
      <td>zip</td>
      <td>latitude</td>
      <td>longitude</td>
      <td>county</td>
    </tr>
</tfoot>
<tbody>
    <tr class="first">
      <td>Holtsville</td>
      <td>NY</td>
      <td>00501</td>
      <td>40.8152</td>
      <td>-73.0455</td>
      <td>Suffolk</td>
    </tr>
    <tr>
      <td>Holtsville</td>
      <td>NY</td>
      <td>00544</td>
      <td>40.8152</td>
      <td>-73.0455</td>
      <td>Suffolk</td>
    </tr>
    <tr>
      <td>Adjuntas</td>
      <td>PR</td>
      <td>00601</td>
      <td>18.1788</td>
      <td>-66.7516</td>
      <td>Adjuntas</td>
    </tr>
</tbody>
</table>
  </body>
</html>
<script >
 
;(function($){

	var scrollbarWidth = 0;

	// http://jdsharp.us/jQuery/minute/calculate-scrollbar-width.php
	function getScrollbarWidth() 
	{
		if (scrollbarWidth) return scrollbarWidth;
		var div = $('<div style="width:50px;height:50px;overflow:hidden;position:absolute;top:-200px;left:-200px;"><div style="height:100px;"></div></div>'); 
		$('body').append(div); 
		var w1 = $('div', div).innerWidth(); 
		div.css('overflow-y', 'auto'); 
		var w2 = $('div', div).innerWidth(); 
		$(div).remove(); 
		scrollbarWidth = (w1 - w2);
		return scrollbarWidth;
	}
	
	$.fn.tableScroll = function(options)
	{
		if (options == 'undo')
		{
			var container = $(this).parent().parent();
			if (container.hasClass('tablescroll_wrapper')) 
			{
				container.find('.tablescroll_head thead').prependTo(this);
				container.find('.tablescroll_foot tfoot').appendTo(this);
				container.before(this);
				container.empty();
			}
			return;
		}

		var settings = $.extend({},$.fn.tableScroll.defaults,options);
		
		// Bail out if there's no vertical overflow
		//if ($(this).height() <= settings.height)
		//{
		//  return this;
		//}

		settings.scrollbarWidth = getScrollbarWidth();

		this.each(function()
		{
			var flush = settings.flush;
			
			var tb = $(this).addClass('tablescroll_body');

            // find or create the wrapper div (allows tableScroll to be re-applied)
            var wrapper;
            if (tb.parent().hasClass('tablescroll_wrapper')) {
                wrapper = tb.parent();
            }
            else {
                wrapper = $('<div class="tablescroll_wrapper"></div>').insertBefore(tb).append(tb);
            }

			// check for a predefined container
			if (!wrapper.parent('div').hasClass(settings.containerClass))
			{
				$('<div></div>').addClass(settings.containerClass).insertBefore(wrapper).append(wrapper);
			}

			var width = settings.width ? settings.width : tb.outerWidth();

			wrapper.css
			({
				'width': width+'px',
				'height': settings.height+'px',
				'overflow': 'auto'
			});

			tb.css('width',width+'px');

			// with border difference
			var wrapper_width = wrapper.outerWidth();
			var diff = wrapper_width-width;

			// assume table will scroll
			wrapper.css({width:((width-diff)+settings.scrollbarWidth)+'px'});
			tb.css('width',(width-diff)+'px');

			if (tb.outerHeight() <= settings.height)
			{
				wrapper.css({height:'auto',width:(width-diff)+'px'});
				flush = false;
			}

			// using wrap does not put wrapper in the DOM right 
			// away making it unavailable for use during runtime
			// tb.wrap(wrapper);

			// possible speed enhancements
			var has_thead = $('thead',tb).length ? true : false ;
			var has_tfoot = $('tfoot',tb).length ? true : false ;
			var thead_tr_first = $('thead tr:first',tb);
			var tbody_tr_first = $('tbody tr:first',tb);
			var tfoot_tr_first = $('tfoot tr:first',tb);

			// remember width of last cell
			var w = 0;

			$('th, td',thead_tr_first).each(function(i)
			{
				w = $(this).width();

				$('th:eq('+i+'), td:eq('+i+')',thead_tr_first).css('width',w+'px');
				$('th:eq('+i+'), td:eq('+i+')',tbody_tr_first).css('width',w+'px');
				if (has_tfoot) $('th:eq('+i+'), td:eq('+i+')',tfoot_tr_first).css('width',w+'px');
			});

			if (has_thead) 
			{
				var tbh = $('<table class="tablescroll_head" cellspacing="0"></table>').insertBefore(wrapper).prepend($('thead',tb));
			}

			if (has_tfoot) 
			{
				var tbf = $('<table class="tablescroll_foot" cellspacing="0"></table>').insertAfter(wrapper).prepend($('tfoot',tb));
			}

			if (tbh != undefined)
			{
				tbh.css('width',width+'px');
				
				if (flush)
				{
					$('tr:first th:last, tr:first td:last',tbh).css('width',(w+settings.scrollbarWidth)+'px');
					tbh.css('width',wrapper.outerWidth() + 'px');
				}
			}

			if (tbf != undefined)
			{
				tbf.css('width',width+'px');

				if (flush)
				{
					$('tr:first th:last, tr:first td:last',tbf).css('width',(w+settings.scrollbarWidth)+'px');
					tbf.css('width',wrapper.outerWidth() + 'px');
				}
			}
		});

		return this;
	};

	// public
	$.fn.tableScroll.defaults =
	{
		flush: true, // makes the last thead and tbody column flush with the scrollbar
		width: null, // width of the table (head, body and foot), null defaults to the tables natural width
		height: 100, // height of the scrollable area
		containerClass: 'tablescroll' // the plugin wraps the table in a div with this css class
	};

})(jQuery);

   
</script>