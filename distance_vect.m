function d=distance_vect( sequence1 , sequence2 , i , j )
    for k = 1:12
        d = sum((sequence1(k,i)-sequence2(k,j)).^2);
    end
end