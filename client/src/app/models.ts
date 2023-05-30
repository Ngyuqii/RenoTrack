export interface Unsplash {
    imageUrl: string;
    imageDes: string;
}

export interface User {
    userId: string;
    userName: string;
    userEmail: string;
    userPassword: string;
}

export interface Inspiration {
  inspoId: number;
  imageUrl: string;
}

export interface Event {
    eventId: number;
    Subject: string;
    StartTime: Date;
    EndTime: Date;
    Description?: string;
    Location?: string;
    googleCalendarUrl?: string;
}

export interface Expense {
  expenseId: number;
  date: string;
  category: string;
  business: string;
  description: string;
  amount: number;
  payment: number;
  balance: number;
}

export interface CategorySum {
  category: string;
  sum: number;
}