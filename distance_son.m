function d=distance_son( sequence1 , sequence2 ,indicei,indicej)
    m = [0,1,1,1,2;...
    1,0,1,1,2;...
    1,1,0,1,1;...
    1,1,1,0,2;...
    2,2,1,2,0];
    m2 = ['X','C','U','W','V'];
    val1 = 0;
    val2 = 0;

    c1 = sequence1(indicei);
    c2 = sequence2(indicej);
    for i=1:5,
        if c1 == m2(i)
            val1 = i;
        end
        if c2 == m2(i)
            val2 = i;
        end
    end
    d = m(val1,val2);
end