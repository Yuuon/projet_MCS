function AfficheChemins(g)
    
    [m,n] = size(g);
    h = inf*ones(m,n);
    h(m,n) = 0;
    %h(1,1) = 0;
    i = m;
    j = n;
    h = pathOptimal(h,g,i,j);
                        
    
    figure;
    imagesc(h);
    
end
%function recursive pour calculer chemin optimal    
function h = pathOptimal(h,g,i,j)
    if(i>1 && j>1)
        pathPossible = find([g(i-1,j),g(i-1,j-1),g(i,j-1)]==1);
            if(size(pathPossible,2)>0)
                
                %s'il y a plusier chioix, position g(i-1,j-1) est advance
                if(find(pathPossible==2))
                    h(i-1,j-1) = 0;
                    h = pathOptimal(h,g,i-1,j-1);
                %situation reste
                else
                    for k = 1 : size(pathPossible,2)
                        switch pathPossible(k)
                            case 1
                                h(i-1,j) = 0;
                                h = pathOptimal(h,g,i-1,j);
                            case 3
                                h(i,j-1) = 0;
                                h = pathOptimal(h,g,i,j-1);
                            otherwise
                                break;
                        end
                    end
                end
            end
    end         
                        
               
end
%chemin = zeros(i,j);
%chemin = inf * ones(i,j);
%d(i,j) = dist_euclid(vec1,vec2) = sqrt(pow2(x1-y1),...)