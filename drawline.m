function drawline(h,j,i);
    x = i;
    y = j;
    if(x ~= 1 && y ~= 1)
        if(h(y-1,x)==1) 
            line([x,y],[x,y-1]);
            y = y - 1;
            drawline(h,y,x);
        end
        if(h(y-1,x-1)==1) 
            line([x,y],[x-1,y-1]);
            x = x - 1;
            y = y - 1;
            drawline(h,y,x);
        end
        if(h(y,x-1)==1) 
            line([x,y],[x-1,y]);
            x = x - 1;
            drawline(h,y,x);
        end
    end
end