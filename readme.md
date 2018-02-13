## Voice Search Prototype

This code can be used to benchmark Speech to text API's. It currently allows for IBM, Microsoft and Google to be tested.


## Setting up expected phrases
In order to measure the quality of the APIs we need to have a set of queries which match the audio dictated in the files which are sent to the API.
These phrases are declared in the QualityMetrics class, 
## Substitution of symbols
If the expected phrases include symbols then the phonetic equivalent of these symbols will need to be included within the SymbolsDictionary class.
## Loading Audio Files
You can load audio files into the AudioRecordings folder. Files should be in the following format:
ID-QuestionID-Accent.wav 
where ID is an id associated to the person, questionid is the id of the question being dictated and accent is the accent or other characteristic you wish to record.
There are some sample Audio files included