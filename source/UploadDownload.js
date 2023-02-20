function popup(nomefile,larghezza, altezza, x, y) {
   win_popup = 
           window.open(nomefile,
                       "popup",
                       "toolbar= 0,location= 0,directories= 0,status= 0,menubar= 0,scrollbars= 1,resizable= 0,copyhistory= 0,width=" + larghezza + ",height=" + altezza);
         if(x && y); 
         {x = parseInt(x);
          y = parseInt(y);
          win_popup.moveTo(x, y); 
         } 
}


function popup_center(mypage,myname,w,h,scroll){
  LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
  TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
  settings = 'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars=1, resizable=1';
  //win = window.open(mypage,myname,settings);
  win = window.open(mypage, null, settings);
  win.focus();
}