$(function(){
    $.fn.extend({
        SimpleTree:function(options){
            
            //初始化参数
            var option = $.extend({
                click:function(a){ }
            },options);
            
            option.tree=this;   /* 在参数对象中添加对当前菜单树的引用，以便在对象中使用该菜单树 */
            
            option._init=function(){
                /*
                 * 初始化菜单展开状态，以及分叉节点的样式
                 */             
                this.tree.find("ul ul").hide(); /* 隐藏所有子级菜单 */
                this.tree.find("ul ul").prev("li").removeClass("open"); /* 移除所有子级菜单父节点的 open 样式 */
                
                this.tree.find("ul ul[show='true']").show();    /* 显示 show 属性为 true 的子级菜单 */
                this.tree.find("ul ul[show='true']").prev("li").addClass("open");   /* 添加 show 属性为 true 的子级菜单父节点的 open 样式 */
            };/* option._init() End */
            
            /* 菜单栏目被点击的时候 */
            
            this.find("li").click(function(){
                var url = $(this).find("a").first().attr("href");
 
                if(!url || url == "#"){
                	if($(this).next("ul").attr("show")=="true"){
                        $(this).next("ul").attr("show","false");                    
                    }else{
                        $(this).next("ul").attr("show","true");
                    }
                    option._init();
                    return false;
                }     
                else
                	return true;
            });
            
            this.find("li").hover(
                function(){
                    $(this).addClass("hover");
                },
                function(){
                    $(this).removeClass("hover");
                }
            );
            
            /* 设置所有父节点样式 */
            this.find("ul").prev("li").addClass("folder");
            

            
            /* 初始化菜单 */
            option._init();
            
        }/* SimpleTree Function End */
        
    });
});