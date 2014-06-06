package demo;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class OneZeroClassifier
{

    private Classifier classifier;

    public Classifier getClassifier()
    {
        return classifier;
    }

    public OneZeroClassifier() throws Exception
    {
        Instances train = DataSource.read(getClass().getResourceAsStream("/OneZero_train.arff"));
        Instances test = DataSource.read(getClass().getResourceAsStream("/OneZero_train.arff"));
        classifier = new RandomForest();
        ((RandomForest)classifier).setNumTrees(50);
        train.setClassIndex(0);
        test.setClassIndex(0);
        classifier.buildClassifier(train);
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(classifier, test);
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }
}