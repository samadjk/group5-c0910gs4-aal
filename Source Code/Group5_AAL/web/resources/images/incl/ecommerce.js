<!--

	startList = function() {
		if (document.all&&document.getElementById) {
			navRoot = document.getElementById("mainNavigation");
			for (i=0; i<navRoot.childNodes.length; i++) {
				node = navRoot.childNodes[i];
				if (node.nodeName=="LI") {
					node.onmouseover=function() {
						this.className+=" over";
					}
					node.onmouseout=function() {
						this.className=this.className.replace(" over", "");
					}
				}
			}
		}
	}
	window.onload=startList;
	


last_tab = 'tab1';
function show(layerName) { 
document.getElementById(layerName).style.display = '';
} 

function hide(layerName) { 
document.getElementById(layerName).style.display = 'none';
} 

function show_next(tab_name) {
document.getElementById(last_tab).className = 'tab';
var curr = document.getElementById(tab_name);
curr.className='tab_hover';
hide(last_tab+'_data');
show(tab_name+'_data');
last_tab=tab_name;
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

//-->
