# Getting Started with Contacts App


## Available Commands

There are next Commands to manipulate with the Contacts storage:

### `help`

Print menu with available commands. Actually, any unknown command will print menu

### `add {Contact name} ; {Contact phone number} ; {Contact email}`

Add a new Contact or update current, if it already exists.  
The `Contact name` field format is any string longer than 3 characters.   
The `Contact phone number` field format is a string of digits starting with +79 and has 9 digits further. 
For example: +79001234567. `Contact phone number` doesn't contain any spaces.  
The `Contact email` field format is a generally accepted standard for email


### `remove {Contact email}`

Remove Contact from the storage.
The `Contact email` field format is a generally accepted standard for email

### `list`

Print all Contacts from the storage

### `dump`

Save all Contacts from the storage to the file: _src/main/resources/contacts.dump.txt_

**The file will be overwritten and the old data will be deleted**

### `exit`

Exit from the App

