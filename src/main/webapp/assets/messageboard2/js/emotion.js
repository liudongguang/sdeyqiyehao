$(document).ready(function(e) {
    ImgIputHandler.Init();
});
var ImgIputHandler={
        facePath:[
{faceName:"em_1",facePath:"1.gif"},
{faceName:"em_2",facePath:"2.gif"},
{faceName:"em_3",facePath:"3.gif"},
{faceName:"em_4",facePath:"4.gif"},
{faceName:"em_5",facePath:"5.gif"},
{faceName:"em_6",facePath:"6.gif"},
{faceName:"em_7",facePath:"7.gif"},
{faceName:"em_8",facePath:"8.gif"},
{faceName:"em_9",facePath:"9.gif"},
{faceName:"em_10",facePath:"10.gif"},
{faceName:"em_11",facePath:"11.gif"},
{faceName:"em_12",facePath:"12.gif"},
{faceName:"em_13",facePath:"13.gif"},
{faceName:"em_14",facePath:"14.gif"},
{faceName:"em_15",facePath:"15.gif"},
{faceName:"em_16",facePath:"16.gif"},
{faceName:"em_17",facePath:"17.gif"},
{faceName:"em_18",facePath:"18.gif"},
{faceName:"em_19",facePath:"19.gif"},
{faceName:"em_20",facePath:"20.gif"},
{faceName:"em_21",facePath:"21.gif"},
{faceName:"em_22",facePath:"22.gif"},
{faceName:"em_23",facePath:"23.gif"},
{faceName:"em_24",facePath:"24.gif"},
{faceName:"em_25",facePath:"25.gif"},
{faceName:"em_26",facePath:"26.gif"},
{faceName:"em_27",facePath:"27.gif"},
{faceName:"em_28",facePath:"28.gif"},
{faceName:"em_29",facePath:"29.gif"},
{faceName:"em_30",facePath:"30.gif"},
{faceName:"em_31",facePath:"31.gif"},
{faceName:"em_32",facePath:"32.gif"},
{faceName:"em_33",facePath:"33.gif"},
{faceName:"em_34",facePath:"34.gif"},
{faceName:"em_35",facePath:"35.gif"},
{faceName:"em_36",facePath:"36.gif"},
{faceName:"em_37",facePath:"37.gif"},
{faceName:"em_38",facePath:"38.gif"},
{faceName:"em_39",facePath:"39.gif"},
{faceName:"em_40",facePath:"40.gif"},
{faceName:"em_41",facePath:"41.gif"},
{faceName:"em_42",facePath:"42.gif"},
{faceName:"em_43",facePath:"43.gif"},
{faceName:"em_44",facePath:"44.gif"},
{faceName:"em_45",facePath:"45.gif"},
{faceName:"em_46",facePath:"46.gif"},
{faceName:"em_47",facePath:"47.gif"},
{faceName:"em_48",facePath:"48.gif"},
{faceName:"em_49",facePath:"49.gif"},
{faceName:"em_50",facePath:"50.gif"},
{faceName:"em_51",facePath:"51.gif"},
{faceName:"em_52",facePath:"52.gif"},
{faceName:"em_53",facePath:"53.gif"},
{faceName:"em_54",facePath:"54.gif"},
{faceName:"em_55",facePath:"55.gif"},
{faceName:"em_56",facePath:"56.gif"},
{faceName:"em_57",facePath:"57.gif"},
{faceName:"em_58",facePath:"58.gif"},
{faceName:"em_59",facePath:"59.gif"},
{faceName:"em_60",facePath:"60.gif"},
{faceName:"em_61",facePath:"61.gif"},
{faceName:"em_62",facePath:"62.gif"},
{faceName:"em_63",facePath:"63.gif"},
{faceName:"em_64",facePath:"64.gif"},
{faceName:"em_65",facePath:"65.gif"},
{faceName:"em_66",facePath:"66.gif"},
{faceName:"em_67",facePath:"67.gif"},
{faceName:"em_68",facePath:"68.gif"},
{faceName:"em_69",facePath:"69.gif"},
{faceName:"em_70",facePath:"70.gif"},
{faceName:"em_71",facePath:"71.gif"},
{faceName:"em_72",facePath:"72.gif"},
{faceName:"em_73",facePath:"73.gif"},
{faceName:"em_74",facePath:"74.gif"},
{faceName:"em_75",facePath:"75.gif"}
        ]
        ,
        Init:function(){
            var isShowImg=false;
            $(".Input_text").focusout(function(){
                $(this).parent().css("border-color", "#cccccc");
                $(this).parent().css("box-shadow", "none");
                $(this).parent().css("-moz-box-shadow", "none");
                $(this).parent().css("-webkit-box-shadow", "none");
            });
            $(".Input_text").focus(function(){
                $(this).parent().css("border-color", "rgba(19,105,172,.75)");
                $(this).parent().css("box-shadow", "0 0 3px rgba(19,105,192,.5)");
                $(this).parent().css("-moz-box-shadow", "0 0 3px rgba(241,39,232,.5)");
                $(this).parent().css("-webkit-box-shadow", "0 0 3px rgba(19,105,252,3)");
            });
            $(".imgBtn").click(function(){

                if(isShowImg==false){
                    isShowImg=true;
                    $(this).parent().prev().animate({marginTop:"-123px"},300);
                    if($(".faceDiv").children().length==0){
                        for(var i=0;i<ImgIputHandler.facePath.length;i++){
                            var addimg="<img title=\""+ImgIputHandler.facePath[i].faceName+"\" src=\"assets/messageboard/face/"+ImgIputHandler.facePath[i].facePath+"\""+"/>";
                            $(".faceDiv").append(addimg);
                        }
                        $(".faceDiv>img").click(function(){
                            isShowImg=false;
                            $(this).parent().animate({marginTop:"0px"},300);
                           // $(this).parent().hide();                           
                            ImgIputHandler.insertAtCursor($(".Input_text")[0],"["+$(this).attr("title")+"]");
                        });
                    }
                }else{
                    isShowImg=false;
                    $(this).parent().prev().animate({marginTop:"0px"},300);
                    //$(this).parent().prev().show();
                }
            });
            $(".postBtn").click(function(){
                alert($(".Input_text").val());
            });
        },
        insertAtCursor:function(myField, myValue) {
            if (document.selection) {
                myField.focus();
                sel = document.selection.createRange();
                sel.text = myValue;
                sel.select();
            } else if (myField.selectionStart || myField.selectionStart == "0") {
                var startPos = myField.selectionStart;
                var endPos = myField.selectionEnd;
                var restoreTop = myField.scrollTop;
                myField.value = myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);
                if (restoreTop > 0) {
                    myField.scrollTop = restoreTop;
                }
                myField.focus();
                myField.selectionStart = startPos + myValue.length;
                myField.selectionEnd = startPos + myValue.length;
            } else {
                myField.value += myValue;
                myField.focus();
            }
        }
    }
 function replace_em(str){
		//str = str.replace(/\</g,'&lt;');
		//str = str.replace(/\>/g,'&gt;');
		//str = str.replace(/\n/g,'<br/>');
		str = str.replace(/\[em_([0-9]*)\]/g,'<img src="assets/messageboard/face/$1.gif" border="0" style="width:18px;height:18px;"/>');
		return str;
	}
