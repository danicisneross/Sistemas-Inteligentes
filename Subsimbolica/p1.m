load /home/mon/matlab/haberman.data
entradas = xlsread('Haberman.xlsx', 'Entradas RNA')';
salidasDeseadas = xlsread('Haberman.xlsx', 'Salidas RNA')';
rna = patternnet([8 10]);
rna = train(rna, entradas, salidasDeseadas);
outputs = sim(rna, entradas);
mse(outputs - salidasDeseadas) % Error cuadrático medio
1 - ans   %La precisión del acierto en la clasificación

% Punto 2
% Calculamos la precisión del conjunto de test
rna = patternnet([8 10]);
[rna, tr] = train(rna, entradas, salidasDeseadas);
InputsTest = entradas(:,tr.testInd);
TargetsTest = salidasDeseadas(:,tr.testInd);
OutputsTest = sim(rna, InputsTest);
precisionTest = 1-confusion(TargetsTest, OutputsTest)

% Punto 4
clear all;
habermanInputs = xlsread('Haberman.xlsx','Entradas RNA')';
habermanTargets = xlsread('Haberman.xlsx','Salidas RNA')';
arquitecturas = {[1 1],[3 1],[5 1],[10 1], [15 1],[5 3],[8 3],[8 5],[10 5]};

for j = 1:length(arquitecturas)
    arquitectura = arquitecturas{j};
    
    precisionEntrenamiento = [];
    precisionValidacion = [];
    precisionTest = [];
    
    for i=1:50
        
        rna = patternnet(arquitectura);
        rna.trainParam.showWindow = false;
        [rna, tr] = train(rna, habermanInputs, habermanTargets);
        habermanOuputs = sim(rna, habermanInputs);
    
        precisionEntrenamiento(end+1) = 1-confusion(habermanTargets(:,tr.trainInd),habermanOuputs(:,tr.trainInd));
        precisionValidacion(end+1)    = 1-confusion(habermanTargets(:,tr.valInd),habermanOuputs(:,tr.valInd));
        precisionTest(end+1)          = 1-confusion(habermanTargets(:,tr.testInd), habermanOuputs(:,tr.testInd));
        
    end 
    
    mediaPrecisionTest = mean(precisionTest);
    desviacionTipica = std(precisionTest);
    fprintf('Arquitectura [%d %d], Entrenamineto: %.2f%% (%.2f), Validacion: %.2f%% (%.2f), Test: %.2f%% (%.2f)\n',arquitectura, ...
            mean(precisionEntrenamiento), std(precisionEntrenamiento), mean(precisionValidacion), ...
            std(precisionValidacion), mean(precisionTest), std(precisionTest));
   
end