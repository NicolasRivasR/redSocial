export interface Post{

    id : string;
    
    content: string;

    videoUrl: string | null;

    imageUrl: string | null;
    
    createdAt: Date;
    
    updatedAt: Date;
    
    userName: string;

}