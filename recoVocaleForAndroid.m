% Reconnaissance vocale par DTW
function num_command=recoVocaleForAndroid()
vocabulaire={'arrete_toi', 'avance', 'droite', 'etat_urgence', 'fais_flip', 'gauche', 'recule', 'tourne_droite', 'tourne_gauche'};
chemin='' ;
nbmots=length(vocabulaire) ;

% Lecture des fichiers audio
% ref

REF = parametrisation('ref.wav'); 


MatriceConfusion=zeros(nbmots, 1);

for mot=1:nbmots
    nomfichier = [ chemin, vocabulaire{mot}, '.wav' ] ;
    HYP{mot} = parametrisation(nomfichier);
    n_ck = size(HYP{mot},1);
    n_cunk = size(REF, 1);
    r = min(n_ck, n_cunk)|2 + 1 ;   
    [D, g, h]=CalculDistanceDTW( REF , HYP{mot} , 'distance_vect' , inf );
    MatriceConfusion(mot, 1) = D;
end

[min, argmin] = min(MatriceConfusion);
if min > 10
    num_command = 0;
else
    num_command = argmin;
end    

disp(MatriceConfusion);

