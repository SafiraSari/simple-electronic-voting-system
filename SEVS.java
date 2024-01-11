// Written by: Safira Sari
// Fall 2022
// --------------------------------------------------------

// Simple Electronic Voting System (SEVS)

import java.util.Scanner;

public class SEVS {

	public static void main(String[] args) {
		
		/*
		 * This program simulates a voting system - Simple Electronic Voting System (SEVS). The user will be able to create their 
		 * list of candidates, view the list of candidates, vote, and display the results of the vote. The program will end once the
		 * user terminates the program (typing 0).
		 */
		
		// Introducing the Scanner class.
		Scanner input = new Scanner (System.in);
				
		System.out.println("Welcome to the Simple Electronic Voting System (SEVS):"
						+ "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

		System.out.println("Please enter a String collection of electoral candidates below:");
		// The user's input is stored in a string array named "Candidate". Each element is separated by the symbol ";".
		String [] Candidate = input.nextLine().split(";");
		// TO BE REMVOED     11, jaNE DoE;   12,JohN Doe  ; 10,TanGO   ZULu;   15,sierra romeo
		
		// Initializing variables outside of the switch statement for easier access.
		String [][] Name_ID = new String [Candidate.length][2];	// 2D string array containing the candidates' ID & name.
		for (int i = 0; i < Candidate.length; i++) 				// It is split by ",". ID is in [i][0], name is in [i][1]. 
		{
			Name_ID [i] = Candidate[i].split(",");
		}
		
		int [] CandidateVotes = new int [Name_ID.length];		// Array containing the votes of each candidate.
		
		int [] position = new int[Name_ID.length];				// Array containing the position of each candidate.
		
		System.out.println("*********************************\n"
						+ "| Code >> Description		|"
						+ "\n*********************************\n"
						+ "|  1   >> Display candidates	|\n"
						+ "|  2   >> Vote a candidate	|\n"
						+ "|  3   >> Add new candidate(s)  |\n"
						+ "|  4   >> Display results	|\n"
						+ "|  0   >> End SEVS		|\n"
						+ "*********************************\n");
		
		// Prompt the user to enter a code - the code inputed will command the program to do a certain task.
		System.out.print("Enter a code, from the aforementioned, that corresponds to your task: ");
		String code = input.next();
		
		/*
		 *  A do-while loop is created, enveloping the switch statement containing the cases. It loops and prompts the user to input a 
		 *  number representing a command. If a user enters an invalid statement, then the prompt is re-shown. The variable "code" is thus
		 *  denoted as a string. The program continues, until it is terminated by ending the SEVS (0).
		 */
		
		do
		{	
			switch (code)
			{
				// The user would like to terminate the program. 
				case "0":
					System.out.println("\nThank you for using our Simple Electronic Voting System (SEVS).");
					input.close();		// Closing the Scanner class.
					System.exit(0);		// Exiting the program.
					break;
				
				// User would like to display the list of candidates, showing their ID and Name.
				case "1": 
					System.out.println("*****************************************\n"
									+ "| ID >> Candidate's Name		|\n"
									+ "*****************************************");
					
					// Displaying all candidate's IDs and names. It is trimmed to remove excess spaces, and names are in upper case. 
					for (int i = 0; i < Name_ID.length; i++) 
					{
						// Name_ID [i] = Candidate[i].split(",");
						System.out.println("| " + Name_ID[i][0].trim() + " >> " + Name_ID[i][1].toUpperCase().trim() + "\t\t\t|");
					}
					System.out.println("*****************************************");
					break;
					
				// The user would like to add a vote for a candidate. 
				case "2":
					System.out.print("\nPlease enter the ID of the candidate you wish to vote for: ");
					String CastedVote = input.next();
					int Winner = -1;
					
					// Their input is matched with the candidate's ID. It is then added to the candidate's vote.
					for (int i = 0; i < Name_ID.length; i++)
					{
						if (CastedVote.equals(Name_ID[i][0].trim()))
						{
							CandidateVotes[i] = CandidateVotes[i] + 1;
							Winner = i;	
							break;
						}
					}
					
					// Displaying who the user voted for.
					System.out.println("Your ballot has been successfully casted for: " + Name_ID[Winner][1].toUpperCase().trim() 
							+ " bearing Candidate ID: " + Name_ID[Winner][0].trim());
					break;
					
				// The user would like to add new electoral candidates to the pre-existing candidates.
				case "3":
					System.out.println("\nPlease enter a String collection of the NEW electoral candidates below: ");
					String junk = input.nextLine();
					String [] NewCandidate = input.nextLine().split(";");
					
					// Creating a temporary array so that new names,votes and positions can be added to their respective original array.
					String [][] TempArrayName = new String[Name_ID.length][2];
					int [] TempArrayVotes = new int [Name_ID.length];
					int [] TempArrayPosition = new int[Name_ID.length];
					
					// Moving the contents of the arrays Name_ID/CandidateVotes/positions into a temporary array.
					for (int i = 0; i < Name_ID.length; i++)
					{
						TempArrayName[i] = Name_ID[i];
						TempArrayVotes[i] = CandidateVotes[i];
						TempArrayPosition[i] = position[i];
					}
					
					// Re-initializing the original arrays (Name_ID, CandidateVotes & positions) to change/increase its length.
					Name_ID = new String[Name_ID.length + NewCandidate.length][2];
					CandidateVotes = new int[Name_ID.length + NewCandidate.length];
					position = new int[Name_ID.length + NewCandidate.length];
					
					// Moving the contents of the temporary array back to the original.
					for (int i = 0; i < TempArrayName.length; i++)
					{
						Name_ID[i] = TempArrayName[i];
						CandidateVotes [i] = TempArrayVotes[i];
						position[i] = TempArrayPosition[i];
					}
					
					// Adding new contents to the original array.
					for (int i = TempArrayName.length; i < Name_ID.length; i++)
					{
						Name_ID[i] = NewCandidate[i - TempArrayName.length].split(",");
						CandidateVotes[i] = 0 ;
						position[i] = 0;
					}	
					
					//      11, jaNE DoE;   12,JohN Doe  ; 10,TanGO   ZULu;   15,sierra romeo
					//  18  ,  yankEE zulu    ; 20, papa  Quebec  ;    22, kilo   LIMA
					
					System.out.println("Succesfully added a NEW set of electoral candidates to the Simple Electronic Voting System (SEVS).");
					break;
					
				// User would like to see the results of the votes, sorted by the candidate's position.
				case "4":
					// Initializing the positions of each candidate to 1 in an array.
			        for (int j = 0; j < position.length; j++) {
			            position[j] = 1;
			        }

			        // Updating the positions of the candidates - if a candidate has more votes, then positions of the others are incremented by 1.
			        for(int i = 0; i < CandidateVotes.length; i++) {
			            for(int j = 0; j < CandidateVotes.length; j++) {
			                if(i == j) {
			                    continue;
			                }
			                if(CandidateVotes[j] < CandidateVotes[i]) {
			                    position[j]++;
			                }
			            }
			        }
			       
	                int printed = 0;
	                int currentPosition = 1;
	                // Parse through the list twice to determine their situation in the end.
	                // If the number of votes we are looking at is less than the number of votes we are comparing it to, we increment its position.
	                // position will show the ranking of each candidate.
	                System.out.println("*****************************************************************\n"
							+ "| Position | Votes/Ballots | ID | Candidate's Name\t\t|\n"
							+ "*****************************************************************");
	                // Printing each candidate in order
	                while(printed < CandidateVotes.length) 
	                {
	                    for (int i = 0; i < CandidateVotes.length; i++) 
	                    {
	                        // Displaying the details of each person with currentPosition
	                        if (position[i] == currentPosition) 
	                        {
	                        	System.out.println("|\t " + position[i] + " |\t\t " + CandidateVotes[i] + " | " + Name_ID[i][0].trim() + " | " 
										+ Name_ID[i][1].toUpperCase().trim() + "\t\t\t|");
	                            printed++;
	                        }
	                    }
	                   currentPosition++;
	                }
	                System.out.println("*****************************************************************");	
	                continue;
			  
			}
			// Prompt the user to enter a code for another command.
			System.out.print("\nKindly continue by entering a valid code, from the aforementioned, which corresponds to your task: ");
			code = input.next();
		}
		while (true);
		
	}

}
