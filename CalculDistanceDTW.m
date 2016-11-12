function [D, g, h]=CalculDistanceDTW( sequence1 , sequence2 , distance , r )
    w0 = 1;
    w1 = 2;
    w2 = 1;
    n = size(sequence1,2);
    m = size(sequence2,2);
    g(1,1) = 0;
    for i = 2:(m + 1)
        g(1,i) = +inf;
        
    end
    for i = 2:(n + 1)
        g(i,1) = +inf;
        
    end
    for i = 2:(n + 1)
        for j = 2:(m + 1)
          if(abs(i-j) <= r)
            [valmin,argmin] = min([g(i-1,j)+w0*feval(distance, sequence1,sequence2,i-1,j-1),
                          g(i-1,j-1)+w1*feval(distance,sequence1,sequence2,i-1,j-1),
                          g(i,j-1)+w2*feval(distance,sequence1,sequence2,i-1,j-1)]);
            
            
            g(i,j) = valmin;
          else g(i,j) = inf;
          end     
        end
    end
    %stocker les chemins possibles
    h(n+1,m+1) = 1;
    for i = (n+1):-1:2
        for j = (m+1):-1:2
          if(abs(i-j)<=r)
          if(h(i,j)==1)
            [a,b] = min([g(i-1,j),g(i-1,j-1),g(i,j-1)]);
            vecargmin = find([g(i-1,j),g(i-1,j-1),g(i,j-1)]==a);
            if(size(vecargmin,2)>0)
                for k = 1 : size(vecargmin,2)
                    if vecargmin(k) == 2
                        h(i-1,j-1) =1;
                        break;
                    elseif vecargmin(k) == 1
                        h(i-1,j) = 1;
                        break;
                    elseif vecargmin(k) == 3
                        h(i,j-1) = 1;
                        break;
                    else
                        break;
                    end
                end
            end
            end
          end
        end
    end
    D = g(n+m)/(n+m);
end

%chemin = zeros(i,j);
%chemin = inf * ones(i,j);
%d(i,j) = dist_euclid(vec1,vec2) = sqrt(pow2(x1-y1),...)