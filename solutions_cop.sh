#Made by Valentin Jabre

target=results/markdown_results.txt
echo "Mode|Nom|Temps|Opt|nbfreq|maxfreq|deltafreq">$target
echo "--|----|----|---|-----|-----|-----">>$target
for file in solution/*
do
	./solution.sh -m $file>>$target
done

